package week3.homework.netty_gateway_01_03.netty.outbooundHandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import week3.homework.netty_gateway_01_03.netty.filter.HttpHeaderResponseFilter;
import week3.homework.netty_gateway_01_03.netty.filter.ResponseHeaderFilter;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler implements OutboundHandler {
    private final ResponseHeaderFilter responseHeaderFilter;
    private final CloseableHttpAsyncClient httpClient;
    private final ExecutorService executorService;
    private final RandomHttpEndpoint randomHttpEndpoint;

    public HttpOutboundHandler() {
        String backendServers = System.getProperty("backendServers","http://localhost:9001,http://localhost:9002");
        this.randomHttpEndpoint = new RandomHttpEndpoint(Arrays.asList(backendServers.split(",")));

        this.responseHeaderFilter = new HttpHeaderResponseFilter();
        int cores = Runtime.getRuntime().availableProcessors();
        this.executorService = Executors.newFixedThreadPool(cores * 2);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response,context) -> 6000)
                .build();

        httpClient.start();
    }

    @Override
    public void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        executorService.submit(() -> handleRequest(fullHttpRequest, ctx));
    }

    private void handleRequest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        System.out.println("handleRequest, url:" + randomHttpEndpoint.getRandomEndpoint() + fullHttpRequest.uri());
        HttpGet httpGet = new HttpGet(randomHttpEndpoint.getRandomEndpoint() + fullHttpRequest.uri());
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("proxy", "netty_proxy");

        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    handleResponse(fullHttpRequest, ctx, httpResponse);
                } catch (Exception e) {
                    System.out.println("handleResponse error:" + e.getMessage());
                    e.printStackTrace();
                } finally {

                }
            }

            @Override
            public void failed(Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    public void handleResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpResponse httpResponse) {
        FullHttpResponse fullHttpResponse = null;

        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));

            responseHeaderFilter.filter(fullHttpResponse, ctx);
        } catch (Exception e) {
            System.out.println("handleResponse error:" + e.getMessage());
            e.printStackTrace();

            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    System.out.println("handleResponse is not keep alive");
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    System.out.println("handleResponse is keep alive");
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                }
            }
            ctx.flush();
        }
    }
}

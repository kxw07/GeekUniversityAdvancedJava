package week3.homework.netty_gateway_01_03.netty.outbooundHandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.util.EntityUtils;
import week3.homework.netty_gateway_01_03.netty.filter.HttpHeaderResponseFilter;
import week3.homework.netty_gateway_01_03.netty.filter.ResponseHeaderFilter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

public class HttpOutboundHandler implements OutboundHandler {
    private final String backendURL;
    private final ResponseHeaderFilter responseHeaderFilter;
    private final CloseableHttpAsyncClient httpClient;
    private final ExecutorService executorService;

    public HttpOutboundHandler(String backendURL) {
        this.backendURL = backendURL;
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
        HttpGet httpGet = new HttpGet(this.backendURL + fullHttpRequest.uri());
        httpGet.setHeader("proxy", "netty_proxy");

        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                try {
                    handleResponse(fullHttpRequest, ctx, httpResponse);
                } catch (Exception e) {
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
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
            responseHeaderFilter.filter(fullHttpResponse, ctx);
        } catch (Exception e) {
            System.out.println("handleResponse failed");
            e.printStackTrace();

            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
        }
    }
}

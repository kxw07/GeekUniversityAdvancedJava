package week3.homework.netty_gateway_01_03.netty.outbooundHandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

public class HttpOutboundHandler implements OutboundHandler {
    HttpClient httpClient;
    String backendURL;
    ExecutorService executorService;

    public HttpOutboundHandler(String backendURL) {
        this.httpClient = HttpClients.createDefault();
        this.backendURL = backendURL;
        this.executorService = Executors.newFixedThreadPool(8);
    }

    @Override
    public void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        HttpGet httpGet = new HttpGet(this.backendURL);

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println(httpResponse);
        } catch (IOException e) {
            System.out.println("execute http request failed.");
            e.printStackTrace();
        }
    }

    public void makeResponse(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String body) {
        FullHttpResponse fullHttpResponse = null;

        try {
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body.getBytes(StandardCharsets.UTF_8)));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("handleTest failed");
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

package homework.netty_gateway_01_03.netty.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

public class HttpHeaderResponseFilter implements ResponseHeaderFilter {

    @Override
    public void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx) {
        fullHttpResponse.headers().set("filter", "outbound_filter");
    }
}
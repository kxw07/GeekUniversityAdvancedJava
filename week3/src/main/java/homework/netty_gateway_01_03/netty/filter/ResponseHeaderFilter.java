package homework.netty_gateway_01_03.netty.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

public interface ResponseHeaderFilter {
    void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx);
}

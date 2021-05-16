package week3.homework.netty_gateway_01_03.netty.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface RequestFilter {
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}

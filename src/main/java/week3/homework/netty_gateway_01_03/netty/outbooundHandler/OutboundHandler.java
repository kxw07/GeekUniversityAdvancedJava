package week3.homework.netty_gateway_01_03.netty.outbooundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface OutboundHandler {
    void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}

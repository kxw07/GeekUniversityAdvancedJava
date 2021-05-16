package week3.homework.netty_gateway_01_03.netty.inboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import week3.homework.netty_gateway_01_03.netty.filter.RequestFilter;
import week3.homework.netty_gateway_01_03.netty.outbooundHandler.OutboundHandler;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    OutboundHandler outboundHandler;
    RequestFilter requestFilter;

    public HttpInboundHandler(OutboundHandler outboundHandler, RequestFilter requestFilter) {
        this.outboundHandler = outboundHandler;
        this.requestFilter = requestFilter;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handle(fullHttpRequest, ctx);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    private void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        this.requestFilter.filter(fullHttpRequest, ctx);
        this.outboundHandler.handle(fullHttpRequest, ctx);
    }
}

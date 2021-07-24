package homework.netty_gateway_01_03.netty.inboundHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import homework.netty_gateway_01_03.netty.filter.RequestHeaderFilter;
import homework.netty_gateway_01_03.netty.outboundHandler.OutboundHandler;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final OutboundHandler outboundHandler;
    private final RequestHeaderFilter[] headerFilter;

    public HttpInboundHandler(OutboundHandler outboundHandler, RequestHeaderFilter[] filters) {
        this.outboundHandler = outboundHandler;
        this.headerFilter = filters;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            handle(fullHttpRequest, ctx);

        } catch (Exception e) {
            System.out.println("HttpInboundHandler channelRead error:" + e.getMessage());
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
        for (RequestHeaderFilter requestHeaderFilter : this.headerFilter) {
           requestHeaderFilter.filter(fullHttpRequest, ctx);
        }
        this.outboundHandler.handle(fullHttpRequest, ctx);
    }
}

package week3.homework.netty_gateway_01_03.netty.inboundHandler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import week3.homework.netty_gateway_01_03.netty.outbooundHandler.HttpOutboundHandler;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        channelPipeline.addLast(new HttpInboundHandler(new HttpOutboundHandler()));
    }
}

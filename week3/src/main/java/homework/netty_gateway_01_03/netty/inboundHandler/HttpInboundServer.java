package homework.netty_gateway_01_03.netty.inboundHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpInboundServer {
    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    int port;
    HttpInboundHandler httpInboundHandler;

    public HttpInboundServer(int port, int bossGroupThreads, int workerGroupThreads, HttpInboundHandler httpInboundHandler) {
        this.bossGroup = new NioEventLoopGroup(bossGroupThreads);
        this.workerGroup = new NioEventLoopGroup(workerGroupThreads);
        this.port = port;
        this.httpInboundHandler = httpInboundHandler;
    }

    public void run() throws InterruptedException {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128) // 最大半連接數量
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true) // 避免等緩衝區滿等太久
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true);


            System.out.println("set options done");

            serverBootstrap.group(this.bossGroup, this.workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInboundInitializer(this.httpInboundHandler));

            Channel ch = serverBootstrap.bind(port).sync().channel();
            System.out.println("Start Netty Server: http://127.0.0.1:" + port);

            ch.closeFuture().sync();
        } finally {
            this.bossGroup.shutdownGracefully();
            this.workerGroup.shutdownGracefully();
        }
    }
}

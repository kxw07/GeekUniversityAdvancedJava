package practice.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServerApplication {
    public static void main(String[] args) throws Exception {
        new NettyServerApplication().run();

        while (true) {

        }
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);

        int port = 8801;

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("set options done");

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInitializer());

            Channel ch = serverBootstrap.bind(port).sync().channel();
            System.out.println("Start Netty Server, url is: http://127.0.0.1:" + 8801);
            ch.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

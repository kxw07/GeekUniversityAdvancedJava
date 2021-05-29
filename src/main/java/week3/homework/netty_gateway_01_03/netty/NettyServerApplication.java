package week3.homework.netty_gateway_01_03.netty;

import week3.homework.netty_gateway_01_03.netty.inboundHandler.HttpInboundHandler;
import week3.homework.netty_gateway_01_03.netty.inboundHandler.HttpInboundServer;
import week3.homework.netty_gateway_01_03.netty.outboundHandler.HttpOutboundHandler;

public class NettyServerApplication {
    public static void main(String[] args) throws Exception {
        String backendServers = System.getProperty("backendServers","http://localhost:9001,http://localhost:9002");

        HttpOutboundHandler httpOutboundHandler = new HttpOutboundHandler(backendServers);
        HttpInboundHandler httpInboundHandler = new HttpInboundHandler(httpOutboundHandler);

        HttpInboundServer httpInboundServer = new HttpInboundServer(8801, 3, 1000, httpInboundHandler);

        try {
            httpInboundServer.run();
        } catch (Exception e) {
            System.out.println("Start Netty server error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}

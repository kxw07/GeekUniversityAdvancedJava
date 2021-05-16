package week3.homework.netty_gateway_01_03.netty;

import week3.homework.netty_gateway_01_03.netty.inboundHandler.HttpInboundServer;

public class NettyServerApplication {
    public static void main(String[] args) throws Exception {

        HttpInboundServer httpInboundServer = new HttpInboundServer(8801, 3, 1000);

        try {
            httpInboundServer.run();
        } catch (Exception e) {
            System.out.println("Start Netty server error");
            e.printStackTrace();
        }
    }
}

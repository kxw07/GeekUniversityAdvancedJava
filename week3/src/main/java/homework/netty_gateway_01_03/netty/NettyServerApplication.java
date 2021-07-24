package homework.netty_gateway_01_03.netty;

import homework.netty_gateway_01_03.netty.filter.HttpHeaderRequestFilter;
import homework.netty_gateway_01_03.netty.filter.HttpHeaderResponseFilter;
import homework.netty_gateway_01_03.netty.filter.RequestHeaderFilter;
import homework.netty_gateway_01_03.netty.filter.ResponseHeaderFilter;
import homework.netty_gateway_01_03.netty.inboundHandler.HttpInboundHandler;
import homework.netty_gateway_01_03.netty.inboundHandler.HttpInboundServer;
import homework.netty_gateway_01_03.netty.outboundHandler.HttpOutboundHandler;

public class NettyServerApplication {
    public static void main(String[] args) throws Exception {
        String backendServers = System.getProperty("backendServers", "http://localhost:9001,http://localhost:9002");

        RequestHeaderFilter[] requestHeaderFilters = new RequestHeaderFilter[]{new HttpHeaderRequestFilter()};
        ResponseHeaderFilter[] responseHeaderFilters = new ResponseHeaderFilter[]{new HttpHeaderResponseFilter()};

        HttpOutboundHandler httpOutboundHandler = new HttpOutboundHandler(backendServers, responseHeaderFilters);
        HttpInboundHandler httpInboundHandler = new HttpInboundHandler(httpOutboundHandler, requestHeaderFilters);

        HttpInboundServer httpInboundServer = new HttpInboundServer(8801, 3, 1000, httpInboundHandler);

        try {
            httpInboundServer.run();
        } catch (Exception e) {
            System.out.println("Start Netty server error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}

package week3.practice.byte_buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelPractice {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        serverSocketChannel.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();

            while(socketChannel.read(byteBuffer) != -1) {
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
        }
    }
}

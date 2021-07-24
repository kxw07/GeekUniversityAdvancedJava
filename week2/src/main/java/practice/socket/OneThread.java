package practice.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class OneThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "Hello, OneThread";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.println(body);
            printWriter.close();
            socket.close();
            System.out.println("OneThread service end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

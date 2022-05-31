package others.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8801);

        while (true) {
            final Socket socket = serverSocket.accept();
            handle(socket);
        }
    }

    private static void handle(Socket socket) {
        try (OutputStream outputStream = socket.getOutputStream();
             PrintWriter printWriter = new PrintWriter(outputStream, true);) {
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello socket";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body); // same with printWriter.print(body)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

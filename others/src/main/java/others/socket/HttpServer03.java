package others.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8801);
        final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                handle(socket);
            });
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

package homework.http_client_06;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpClientTest {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8801/test");

        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            System.out.println(httpResponse);
        } catch (IOException e) {
            System.out.println("execute http request failed.");
            e.printStackTrace();
        }
    }
}

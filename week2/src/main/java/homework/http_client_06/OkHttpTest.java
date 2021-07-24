//package week2.homework.http_client_06;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import java.io.IOException;
//
//public class OkHttpTest {
//    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://localhost:8801")
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//        } catch (IOException e) {
//            System.out.println("execute http request failed.");
//            e.printStackTrace();
//        }
//    }
//}

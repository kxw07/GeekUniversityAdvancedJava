package practice.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class ListenableFuturePractice {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        ListenableFuture<String> listenableFuture = service.submit(new Task());
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String s) {
                ListenableFuturePractice.success(s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                ListenableFuturePractice.failure();
            }
        }, service);

        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3_000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        completableFuture.whenComplete(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                ListenableFuturePractice.success("CompletableFuture on complete...");
            }
        });

        while(true) {

        }
    }

    public static void success(String s){
        System.out.println("onSuccess callback..." + s);
    }

    public static void failure(){
        System.out.println("onFailure callback...");
    }
}

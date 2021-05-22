package week4.homework.main_wait_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ACompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // normal
        String result1 = CompletableFuture.supplyAsync(() -> "CompletableFutureResult").get();

        System.out.println("Result1: " + result1);

        // async then
        CompletableFuture.supplyAsync(() -> {
            return "Step 1 result";
        }).thenAccept(v -> {
            System.out.println("v: " + v);
        });

        // combine two async
        String result2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Sleep 1");
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "One";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("Sleep 2");
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Two";
        }), (v1, v2) -> {
            return v1 + " " + v2;
        }).join();

        System.out.println("Result2: " + result2);

        // competition
        String result3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "One";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Two";
        }), v -> {
            return v;
        }).join();

        System.out.println("Result3: " + result3);

        // exception
        String result4 = CompletableFuture.supplyAsync(() -> {
            if(true) {
                throw new RuntimeException("exception test!");
            }
            return "Dummy value";
        }).exceptionally( e -> {
            System.out.println(e.getMessage());
            return "Value in exception";
        }).join();

        System.out.println("Result4: " + result4);
    }
}

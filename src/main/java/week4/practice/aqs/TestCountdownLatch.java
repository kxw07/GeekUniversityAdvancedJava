package week4.practice.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class TestCountdownLatch {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

        List<CompletableFuture> list = new ArrayList<>(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CountdownLatchTask(countDownLatch));
            list.add(future);
        }

        countDownLatch.await();
        System.out.println("End main thread");
    }
}

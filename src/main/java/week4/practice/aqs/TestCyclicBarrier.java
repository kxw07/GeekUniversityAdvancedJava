package week4.practice.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class TestCyclicBarrier {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfThreads = 5;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads);
        List<CompletableFuture> list = new ArrayList<>(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CyclicBarrierTask(cyclicBarrier));
            list.add(future);
        }

        for (CompletableFuture future : list) {
            future.get();
        }

        cyclicBarrier.reset();
    }
}

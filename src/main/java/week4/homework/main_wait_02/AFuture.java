package week4.homework.main_wait_02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new CustomThreadPoolExecutor().getThreadPoolExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call start...");
                Thread.sleep(3_000);
                return "FutureResult";
            }
        });

        System.out.println("Result: " + future.get());
    }
}

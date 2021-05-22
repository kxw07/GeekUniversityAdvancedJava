package week4.homework.main_wait_02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class AFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new CustomThreadPoolExecutor().getThreadPoolExecutor();
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call start...");
                Thread.sleep(3_000);
                return "FutureTaskResult";
            }
        });

        executorService.submit(futureTask);
        futureTask.cancel(true);
        System.out.println("Result: " + futureTask.get());
    }
}

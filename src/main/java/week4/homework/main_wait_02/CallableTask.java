package week4.homework.main_wait_02;

import java.util.concurrent.*;

public class CallableTask implements Callable<String> {
    @Override
    public String call() throws InterruptedException {
        System.out.println("CallableTask Start...");
        Thread.sleep(5_000);
        System.out.println("CallableTask End...");
        return "End of CallableTask";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new CallableTask());

        System.out.println("Main Thread Waiting...");
        String result = future.get();
        System.out.println("Future Result: " + result);


        System.out.println("End of Main Thread");
        executorService.shutdown();
    }
}

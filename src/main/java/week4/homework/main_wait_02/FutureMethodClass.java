package week4.homework.main_wait_02;

import java.util.concurrent.*;

public class FutureMethodClass {
    private final ThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor().getThreadPoolExecutor();

    public Future<String> callFutureMethod() {
        return threadPoolExecutor.submit(() -> {
            try {
                System.out.println("callFutureMethod start...");
                Thread.sleep(5_000);
                System.out.println("callFutureMethod end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "callFutureMethodResult";
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureMethodClass futureMethodClass = new FutureMethodClass();
        Future<String> future = futureMethodClass.callFutureMethod();

        System.out.println("Main Thread Waiting...");
        String result = future.get();
        System.out.println("Future Result: " + result);
    }
}

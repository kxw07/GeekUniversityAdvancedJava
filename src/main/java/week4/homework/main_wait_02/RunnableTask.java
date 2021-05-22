package week4.homework.main_wait_02;

import java.util.concurrent.*;

public class RunnableTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("RunnableTask Start...");
            Thread.sleep(5_000);
            System.out.println("RunnableTask End...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CustomThreadFactory customThreadFactory = new CustomThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 1, 1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(10), customThreadFactory
        );
        Future<String> future = threadPoolExecutor.submit(new RunnableTask(), "RunnableTaskResult");

        System.out.println("Main Thread Waiting...");
        String result = future.get();
        System.out.println("Future result:" + result);


        System.out.println("End of Main Thread");
    }
}

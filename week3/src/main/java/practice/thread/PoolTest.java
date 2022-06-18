package practice.thread;

import java.util.Date;
import java.util.concurrent.*;

public class PoolTest {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        // case 1: should throw runtime exception
        final Runnable task = new Runnable() {
            @Override
            public void run() {
                int a = 10 / 0;
            }
        };

        final Future<?> future = executorService.submit(task);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // case 2: return given value
        final Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("return default value when task finished");
            }
        };

        final Future<String> futureWithDefaultValue = executorService.submit(task2, "finished");

        try {
            System.out.println(futureWithDefaultValue.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // case 3: callable can throw exception
        final Callable<String> callableTask = new Callable() {
            @Override
            public String call() throws SelfException {
                throw new SelfException("Hello World");
            }
        };

        final Future<String> futureCallable = executorService.submit(callableTask);

        try {
            System.out.println(futureCallable.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // case 4: task will be executed after submit
        // case 5: can use awaitTermination to trigger shutdownNow when task executed too long
        final Runnable pendingTask = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        final Future<String> pendingTaskFuture = executorService.submit(pendingTask, "pending task finished");
//        try {
//            Thread.sleep(5000);
//            System.out.println(new Date());
//            System.out.println(pendingTaskFuture.get());;
//            System.out.println(new Date());
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }


        System.out.println("shutdown thread pool");
        System.out.println("before awaitTermination:" + new Date());
        while (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
            System.out.println("inside awaitTermination:" + new Date());
            System.out.println("wait timeout needs to force shutdown pool");
            executorService.shutdownNow();
        }
    }

    static class SelfException extends Exception {
        public SelfException(String msg) {
            super(msg);
        }
    }
}
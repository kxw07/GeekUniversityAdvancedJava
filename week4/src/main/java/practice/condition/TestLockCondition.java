package practice.condition;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class TestLockCondition {
    public static void main(String[] args) {
        final LockCondition lockCondition = new LockCondition();
        LockSupport.park(lockCondition);

        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        final Runnable putTask = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        final Instant now = Instant.now();
                        System.out.println("put:" + now);
                        lockCondition.put(now);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        final Runnable takeTask = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("take:" + lockCondition.take());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        executorService.submit(putTask);
        executorService.submit(takeTask);
    }
}

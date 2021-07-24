package practice.aqs;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountdownLatchTask implements Runnable {
    private final CountDownLatch countDownLatch;

    public CountdownLatchTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        int millis = new Random().nextInt(10_000);

        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            this.countDownLatch.countDown();
            System.out.println("My task is done:" + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("Task run error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}

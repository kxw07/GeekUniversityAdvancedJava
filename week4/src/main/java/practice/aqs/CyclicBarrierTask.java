package practice.aqs;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTask implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTask(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int millis = new Random().nextInt(10_000);

        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            this.cyclicBarrier.await();
            System.out.println("開動：" + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

package practice.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreCounter {
    private int count = 0;
    private Semaphore readSemaphore = new Semaphore(100);
    private Semaphore writeSemaphore = new Semaphore(2, true);

    public void addAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            System.out.println("使用中:" + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(2_000);
            System.out.println("釋放:" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeSemaphore.release();
        }
    }

    public int get() {
        try {
            readSemaphore.acquireUninterruptibly();
            return count;
        } finally {
            readSemaphore.release();
        }
    }
}

package week4.practice.aqs;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter {
    private int count = 0;
    private Semaphore readSemaphore = new Semaphore(100);
    private Semaphore writeSemaphore = new Semaphore(1, true);

    public int addAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            return ++count;
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

package practice.lock_support;

import java.util.concurrent.locks.LockSupport;

public class TryLockSupport implements Runnable {

    private final Thread thread;

    public TryLockSupport(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("unpark thread");
            LockSupport.unpark(thread);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

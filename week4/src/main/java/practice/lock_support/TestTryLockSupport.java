package practice.lock_support;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

public class TestTryLockSupport {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        final TryLockSupport tryLockSupport = new TryLockSupport(thread);

        final Thread task = new Thread(tryLockSupport);
        task.start();

        System.out.println("wait for unpark: " + new Date());
        LockSupport.park();
        System.out.println("unpark: " + new Date());
    }
}

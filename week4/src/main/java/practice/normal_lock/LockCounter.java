package practice.normal_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private int count;
    private final Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return ++count;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return count;
    }
}

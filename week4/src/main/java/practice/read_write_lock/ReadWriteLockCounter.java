package practice.read_write_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private int count = 0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public int addAndGetWithWriteLock() {
        try {
            readWriteLock.writeLock().lock();
            return ++count;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int addAndGetWithReadLock() {
        try {
            readWriteLock.readLock().lock();
            return ++count;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public int get() {
        return count;
    }
}

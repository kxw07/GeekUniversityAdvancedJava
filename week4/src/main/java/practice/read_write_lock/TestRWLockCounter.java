package practice.read_write_lock;

import java.util.stream.IntStream;

public class TestRWLockCounter {
    public static void main(String[] args) {
        int loop_number = 100_000;
        ReadWriteLockCounter readWriteLockCounter1 = new ReadWriteLockCounter();

        IntStream.range(0, loop_number).parallel().forEach(idx -> {
            readWriteLockCounter1.addAndGetWithWriteLock();
        });
        System.out.println("Result 1, correct with write lock:" + readWriteLockCounter1.get());

        ReadWriteLockCounter readWriteLockCounter2 = new ReadWriteLockCounter();

        IntStream.range(0, loop_number).parallel().forEach(idx -> {
            readWriteLockCounter2.addAndGetWithReadLock();
        });
        System.out.println("Result 2, error with read lock:" + readWriteLockCounter2.get());
    }
}

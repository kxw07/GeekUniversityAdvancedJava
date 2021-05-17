package week4.practice.normal_lock;

import java.util.stream.IntStream;

public class TestLockCounter {
    public static void main(String[] args) {
        int loop_number = 100_000;
        LockCounter lockCounter = new LockCounter();
        IntStream.range(0, loop_number).parallel().forEach(idx -> {
            lockCounter.addAndGet();
        });

        System.out.println("Result:" + lockCounter.get());
    }
}

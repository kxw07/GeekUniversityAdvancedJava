package week4.practice.atomic;

import java.time.Instant;
import java.util.stream.IntStream;

public class TestAtomic {
    public static void main(String[] args) {
        long start = Instant.now().getEpochSecond();
        int loop_number = 100_000;

        AtomicIntCounter atomicIntCounter = new AtomicIntCounter();
        IntStream.range(0, loop_number).parallel().forEach(idx -> {
            atomicIntCounter.addAndGet();
        });
        long end = Instant.now().getEpochSecond();
        System.out.println("Result:" + atomicIntCounter.get());
    }
}

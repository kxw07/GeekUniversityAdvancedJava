package week4.practice.aqs;

import java.util.stream.IntStream;

public class TestSemaphore {
    public static void main(String[] args) {
        int loop_number = 100_000;

        SemaphoreCounter semaphoreCounter = new SemaphoreCounter();
        IntStream.range(0, loop_number).parallel().forEach(idx -> {
            semaphoreCounter.addAndGet();
        });
        System.out.println("Result:" + semaphoreCounter.get());
    }
}

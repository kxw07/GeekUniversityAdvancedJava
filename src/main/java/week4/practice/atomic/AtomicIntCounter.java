package week4.practice.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntCounter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int addAndGet(){
        return atomicInteger.incrementAndGet();
    }

    public int get() {
        return atomicInteger.get();
    }
}

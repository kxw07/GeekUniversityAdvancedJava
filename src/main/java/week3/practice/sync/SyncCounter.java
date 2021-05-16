package week3.practice.sync;

public class SyncCounter {
    //    private volatile int count = 0; //not work when multi threads reads and writes
    private int count = 0;

    public synchronized void increase() {
        count = count + 1;
    }
//    public void increaseCount() {
//        synchronized (this) {
//            count = count + 1;
//        };
//    }

    public int get() {
        return count;
    }
}

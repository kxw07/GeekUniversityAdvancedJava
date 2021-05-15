package week3.practice.sync;

public class SyncCounter {
    private int count = 0;

    public synchronized void increaseCount() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}

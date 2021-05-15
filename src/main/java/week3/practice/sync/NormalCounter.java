package week3.practice.sync;

public class NormalCounter {
    private int count = 0;

    public void increaseCount() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}

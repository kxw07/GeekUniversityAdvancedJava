package week3.practice.sync;

public class NormalCounter {
    private int count = 0;

    public void increase() {
        count = count + 1;
    }

    public int get() {
        return count;
    }
}

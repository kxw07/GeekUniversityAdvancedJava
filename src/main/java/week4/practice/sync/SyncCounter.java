package week4.practice.sync;

public class SyncCounter {
    private int count;

    public void add() throws InterruptedException {
        synchronized (this) {
            System.out.println("add...");
            Thread.sleep(5_000);
            this.count++;
            System.out.println("end add...");
        }

    }

    public void subtract() throws InterruptedException {
        synchronized (this) {
            System.out.println("remove...");
            Thread.sleep(5_000);
            this.count--;
            System.out.println("end remove...");
        }

    }
}

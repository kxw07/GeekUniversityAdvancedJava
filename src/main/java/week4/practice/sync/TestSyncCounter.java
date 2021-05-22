package week4.practice.sync;

public class TestSyncCounter {
    public static void main(String[] args) throws InterruptedException {
        SyncCounter syncCounter = new SyncCounter();
        Thread thread1 = new Thread(() -> {
            try {
                syncCounter.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                syncCounter.subtract();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}

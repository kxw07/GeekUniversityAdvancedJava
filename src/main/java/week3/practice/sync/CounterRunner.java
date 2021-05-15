package week3.practice.sync;

public class CounterRunner {
    public static void main(String[] args) throws InterruptedException {
        testNormalCounter();
        testSyncCounter();
    }

    public static void testNormalCounter() throws InterruptedException {
        int loop = 100_000;

        NormalCounter normalCounter = new NormalCounter();
        for (int i = 0; i < loop; i++) {
            normalCounter.increaseCount();
        }
        System.out.println("Single Thread NormalCounter result:" + normalCounter.getCount());

        NormalCounter normalCounter2 = new NormalCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < loop/2; i++) {
                normalCounter2.increaseCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < loop/2; i++) {
                normalCounter2.increaseCount();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Multiple Thread NormalCounter result:" + normalCounter2.getCount());
    }

    public static void testSyncCounter() throws InterruptedException {
        int loop = 100_000;

        SyncCounter syncCounter = new SyncCounter();
        for (int i = 0; i < loop; i++) {
            syncCounter.increaseCount();
        }
        System.out.println("Single Thread SyncCounter result:" + syncCounter.getCount());

        SyncCounter syncCounter2 = new SyncCounter();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < loop/2; i++) {
                syncCounter2.increaseCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < loop/2; i++) {
                syncCounter2.increaseCount();
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Multiple Thread SyncCounter result:" + syncCounter2.getCount());
    }
}

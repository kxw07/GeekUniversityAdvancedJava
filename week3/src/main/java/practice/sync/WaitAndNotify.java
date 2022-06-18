package practice.sync;

public class WaitAndNotify {
    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "product-thread");

        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "consume-thread");

        t1.start();
        t2.start();
    }
}

class MethodClass {
    // 定义生产最大量
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(1000);
            if (productCount >= MAX_COUNT) {
                System.out.println("货舱已满,,.不必再生产");

                wait();
            } else {
                productCount++;
            }

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(1000);
            if (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                wait();
            } else {
                productCount--;
            }

            notifyAll();
        }
    }
}

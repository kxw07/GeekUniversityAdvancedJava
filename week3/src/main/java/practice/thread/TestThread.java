package practice.thread;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {

        String a = "";
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (a) {
                        System.out.println("Runnable is sleeping");
                        Thread.sleep(3000);
                        System.out.println("Runnable is up");
                        a.wait();
                        System.out.println("Runnable finished");
                    }
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Main thread is sleeping");
        Thread.sleep(1000);
        System.out.println("Main thread is up");

        synchronized (a) {
            System.out.println("Ready to notify all");
            a.notifyAll();
        }

//        thread.interrupt();
//        thread.join();
    }
}

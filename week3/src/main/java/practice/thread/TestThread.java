package practice.thread;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("I'm Runnable");
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        thread.interrupt();

        thread.join();
    }
}

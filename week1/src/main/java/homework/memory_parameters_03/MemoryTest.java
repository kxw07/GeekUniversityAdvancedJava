package homework.memory_parameters_03;

public class MemoryTest {
//    javac week1/homework/memory_parameters/MemoryTest.java
//    java -Xss160k week1/homework/memory_parameters/MemoryTest
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new TestStack("Thread1"));
        Thread thread2 = new Thread(new TestStack("Thread2"));
        Thread thread3 = new Thread(new TestStack("Thread3"));
        Thread thread4 = new Thread(new TestStack("Thread4"));

        thread1.start();
        Thread.sleep(5000L);

        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class TestStack implements Runnable {
        private String name;

        public TestStack(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            callLoop(1);
        }

        public void callLoop(int count) {
            System.out.println(name + "_" + count);
            count++;
            callLoop(count);
        }
    }
}

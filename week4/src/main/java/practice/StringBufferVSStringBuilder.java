package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringBufferVSStringBuilder {
    public static void main(String[] args) {
        testStringBuffer();
        testStringBuilder(); // not thread safe
    }

    private static void testStringBuffer() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final StringBuffer stringBuffer = new StringBuffer();

        final Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    stringBuffer.append(i);
                }
            }
        };

        executorService.submit(task);
        executorService.submit(task);

        executorService.shutdown();

        System.out.println(stringBuffer);
    }

    private static void testStringBuilder() {
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        final StringBuilder stringBuilder = new StringBuilder();

        final Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    stringBuilder.append(i);
                }
            }
        };

        executorService.submit(task);
        executorService.submit(task);

        executorService.shutdown();

        System.out.println(stringBuilder);
    }
}

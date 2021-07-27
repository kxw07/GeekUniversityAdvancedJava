package practice.guava;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
//        throw new Exception("test failure");
        Thread.sleep(3_000);
        return "Sleep finish...";
    }
}

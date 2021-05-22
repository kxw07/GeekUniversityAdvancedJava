package week4.homework.main_wait_02;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor {
    private final CustomThreadFactory customThreadFactory = new CustomThreadFactory();
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 1,
            TimeUnit.MINUTES, new LinkedBlockingDeque<>(10), customThreadFactory
    );

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return this.threadPoolExecutor;
    }
}

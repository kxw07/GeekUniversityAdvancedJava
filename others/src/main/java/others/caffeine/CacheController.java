package others.caffeine;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheController {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Instant.now().toString() + ", first: " + CacheService.get());

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                String str = CacheService.get();
                System.out.println(Instant.now().toString() + ", runnable1: " + str);
            }
        };

        final Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                String str = CacheService.get();
                System.out.println(Instant.now().toString() + ", runnable2: " + str);
            }
        };

        final Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                String str = CacheService.get();
                System.out.println(Instant.now().toString() + ", runnable3: " + str);
            }
        };

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(runnable1, 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(runnable2, 0, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(runnable3, 0, 4, TimeUnit.SECONDS);

        while (true){

        }
    }
}

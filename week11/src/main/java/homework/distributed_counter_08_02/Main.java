package homework.distributed_counter_08_02;

import homework.RedisUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        RedisUtil.getConnection().set("counter".getBytes(), String.valueOf(100).getBytes());

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        scheduledExecutorService.schedule(new DecreaseTask(), 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new DecreaseTask(), 5, TimeUnit.SECONDS);

        while (true) {

        }
    }
}

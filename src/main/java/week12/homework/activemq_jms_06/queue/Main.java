package week12.homework.activemq_jms_06.queue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new QueueProducer(), 3, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new QueueConsumer(), 3, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new QueueConsumer(), 3, 1, TimeUnit.SECONDS);
    }
}

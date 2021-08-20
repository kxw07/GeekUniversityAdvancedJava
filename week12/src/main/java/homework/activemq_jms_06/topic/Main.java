package homework.activemq_jms_06.topic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new TopicProducer(), 3, 3, TimeUnit.SECONDS);

        Thread thread1 = new Thread(new TopicConsumer());
        thread1.start();

        Thread thread2 = new Thread(new TopicConsumer());
        thread2.start();

//        scheduledExecutorService.scheduleAtFixedRate(new TopicConsumer(), 3, 1, TimeUnit.SECONDS);
//        scheduledExecutorService.scheduleAtFixedRate(new TopicConsumer(), 3, 1, TimeUnit.SECONDS);
    }
}

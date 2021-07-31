package homework.mq_06;

import homework.mq_06.core.KaiBroker;
import homework.mq_06.core.KaiConsumer;
import homework.mq_06.core.KaiProducer;

public class KaiMQDemo {
    public static void main(String[] args) throws InterruptedException {
        final KaiBroker kaiBroker = new KaiBroker();
        kaiBroker.createTopic("mq-test");

        KaiProducer kaiProducer = kaiBroker.createProducer();
        kaiProducer.send("mq-test", "test001");
        kaiProducer.send("topic-not-found", "test001");

        KaiConsumer kaiConsumer = kaiBroker.createConsumer();
        kaiConsumer.subscribe("mq-test");

        final boolean[] workingFlag = new boolean[1];
        workingFlag[0] = true;
        Thread thread = new Thread(() -> {
            while (workingFlag[0]) {
                try {
                    System.out.println("Consumer consume messages: " + kaiConsumer.poll());
                } catch (Exception e) {
                    System.out.println("Consumer poll failed.");
                }
            }
        });
        thread.start();

        kaiProducer.send("mq-test", "test002");
        kaiProducer.send("mq-test", "test003");

        Thread.sleep(5000L);

        workingFlag[0] = false;
    }
}

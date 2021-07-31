package homework.mq_06;

import homework.mq_06.core.KaiBroker;
import homework.mq_06.core.KaiConsumer;
import homework.mq_06.core.KaiProducer;

public class KaiMQDemo {
    public static void main(String[] args) {
        final KaiBroker kaiBroker = new KaiBroker();
        kaiBroker.createTopic("mq-test");

        KaiProducer kaiProducer = kaiBroker.createProducer();
        kaiProducer.send("mq-test", "test001");

        KaiConsumer kaiConsumer = kaiBroker.createConsumer();
        kaiConsumer.subscribe("mq-test");
        kaiConsumer.poll();
    }
}

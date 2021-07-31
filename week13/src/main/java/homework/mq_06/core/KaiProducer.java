package homework.mq_06.core;

public class KaiProducer {

    private final KaiBroker kaiBroker;

    public KaiProducer(KaiBroker kaiBroker) {
        this.kaiBroker = kaiBroker;
    }

    public void send(String topicName, String message) {
        this.kaiBroker.findTopic(topicName).send(message);
    }
}

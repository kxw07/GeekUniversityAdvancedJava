package homework.mq_06.core;

public class KaiProducer {

    private final KaiBroker kaiBroker;

    public KaiProducer(KaiBroker kaiBroker) {
        this.kaiBroker = kaiBroker;
    }

    public void send(String topicName, String message) {
        KaiQueue kaiQueue = this.kaiBroker.findTopic(topicName);
        if (null == kaiQueue) {
            System.out.println("Topic[" + topicName + "] not found");
            return;
        }

        kaiQueue.send(message);
    }
}

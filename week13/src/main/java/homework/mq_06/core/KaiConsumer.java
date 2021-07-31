package homework.mq_06.core;

public class KaiConsumer {

    private final KaiBroker kaiBroker;
    private KaiQueue kaiQueue;

    public KaiConsumer(KaiBroker kaiBroker) {
        this.kaiBroker = kaiBroker;
    }

    public void subscribe(String topicName) {
        this.kaiQueue = this.kaiBroker.findTopic(topicName);
    }

    public String poll() throws InterruptedException {
        return this.kaiQueue.poll();
    }
}

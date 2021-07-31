package homework.mq_06.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class KaiBroker {
    private final Map<String, KaiQueue> topicMap = new ConcurrentHashMap<>(64);
    private static final int CAPACITY = 10000;

    public void createTopic(String topicName) {
        this.topicMap.putIfAbsent(topicName, new KaiQueue(CAPACITY));
    }

    public KaiProducer createProducer() {
        return new KaiProducer(this);
    }

    public KaiConsumer createConsumer() {
        return new KaiConsumer(this);
    }

    public KaiQueue findTopic(String topicName) {
        return this.topicMap.get(topicName);
    }
}

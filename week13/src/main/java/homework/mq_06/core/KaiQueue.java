package homework.mq_06.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class KaiQueue {

    private LinkedBlockingQueue<String> linkedBlockingQueue;

    public KaiQueue(int capacity) {
        this.linkedBlockingQueue = new LinkedBlockingQueue(capacity);
    }

    public void send(String message) {
        this.linkedBlockingQueue.offer(message);
    }

    public String poll() throws InterruptedException {
        return this.linkedBlockingQueue.poll(1000L, TimeUnit.MILLISECONDS);
    }
}

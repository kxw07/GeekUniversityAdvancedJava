package homework.kafka_cluster_01.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "kai-test", groupId = "group1")
    public void listenGroup1(String msg) {
        System.out.println("Received Message in group1: " + msg);
    }
}



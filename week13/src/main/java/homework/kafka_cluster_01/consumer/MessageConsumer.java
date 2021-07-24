package homework.kafka_cluster_01.consumer;

import homework.kafka_cluster_01.pojo.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "test-normal-msg", groupId = "group1")
    public void listenGroup1(String msg) {
        System.out.println("Received Message in group1: " + msg);
    }

    @KafkaListener(topics = "test-normal-msg", groupId = "group2", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
    }

    @KafkaListener(topics = "test-object-msg", groupId = "group1", containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        System.out.println("Received Greeting Message: " + greeting);
    }
}



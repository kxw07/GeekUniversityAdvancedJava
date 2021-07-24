package week13.homework.kafka_cluster_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class KafkaApplication {

//    @Autowired
//    private static MessageProducer messageProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class);
//        messageProducer.send("test msg:" + Instant.now().getEpochSecond());
    }
}

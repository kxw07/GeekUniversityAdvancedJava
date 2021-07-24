package homework.kafka_cluster_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {

//    @Autowired
//    private static MessageProducer messageProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class);
//        messageProducer.send("test msg:" + Instant.now().getEpochSecond());
    }
}

package homework.kafka_cluster_01;

import homework.kafka_cluster_01.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

    @Autowired
    private MessageProducer messageProducer;


    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        messageProducer.send("AAA");
    }
}

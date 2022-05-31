package homework.kafka_cluster_01;

import homework.kafka_cluster_01.consumer.MessageConsumer;
import homework.kafka_cluster_01.pojo.Greeting;
import homework.kafka_cluster_01.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageConsumer messageConsumer;


    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
//        messageProducer.send("AAA");
//        messageProducer.asyncSend("BBB");
//
//        messageProducer.send("Hello World");
//
//        messageProducer.send(new Greeting("Jack", "Good Morning"));
    }
}

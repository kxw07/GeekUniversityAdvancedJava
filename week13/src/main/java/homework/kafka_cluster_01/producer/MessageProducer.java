package homework.kafka_cluster_01.producer;

import homework.kafka_cluster_01.pojo.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    public void send(String msg) {
        kafkaTemplate.send("test-normal-msg", msg);
    }

    public void asyncSend(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test-normal-msg", msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Unable to send message=[" + msg + "] due to : " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }

    public void send(Greeting greeting) {
        greetingKafkaTemplate.send("test-object-msg", greeting);
    }
}

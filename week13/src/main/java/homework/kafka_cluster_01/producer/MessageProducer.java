package homework.kafka_cluster_01.producer;

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

    public void send(String msg) {
        kafkaTemplate.send("kai-test", msg);
    }

    public void asyncSend(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("kai-test", msg);

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
}

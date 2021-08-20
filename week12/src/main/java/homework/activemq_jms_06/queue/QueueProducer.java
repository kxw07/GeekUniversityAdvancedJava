package homework.activemq_jms_06.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;
import java.time.Instant;

public class QueueProducer implements Runnable {
    private static final Logger logger = LogManager.getLogger(QueueProducer.class);

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("TEST.QUEUE");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            String text = "Hello world! From Producer " + Thread.currentThread().getName() + " At " + Instant.now().getEpochSecond();
            TextMessage textMessage = session.createTextMessage(text);

            producer.send(textMessage);

            session.close();
            connection.close();
        } catch (Exception e) {
            logger.error("produce message fail", e);
        }
    }
}

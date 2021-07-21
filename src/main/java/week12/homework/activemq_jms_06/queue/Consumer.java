package week12.homework.activemq_jms_06.queue;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class Consumer {
    private static final Logger logger = LogManager.getLogger(Consumer.class);

    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("TEST.QUEUE");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                logger.info("consume message: " + text);
            } else {
                logger.info("consume message: " + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            logger.error("consume message fail", e);
        }
    }
}

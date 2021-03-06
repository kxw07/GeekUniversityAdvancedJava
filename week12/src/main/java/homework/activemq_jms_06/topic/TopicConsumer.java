package homework.activemq_jms_06.topic;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class TopicConsumer implements Runnable, ExceptionListener {
    private static final Logger logger = LogManager.getLogger(TopicConsumer.class);

    @Override
    public void run() {
        try {
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("TEST.TOPIC");

            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        try {
                            TextMessage textMessage = (TextMessage) message;
                            String text = textMessage.getText();
                            logger.info(Thread.currentThread().getName() + " consume message: " + text);
                        } catch (JMSException e) {
                            logger.error("consume message failed.");
                        }
                    }
                });
            }

//            consumer.close();
//            session.close();
//            connection.close();
        } catch (Exception e) {
            logger.error("consume message fail", e);
        }
    }

    @Override
    public void onException(JMSException e) {
        logger.error("JMS Exception occurred.  Shutting down client.");
    }
}

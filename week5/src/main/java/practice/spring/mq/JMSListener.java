package practice.spring.mq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
public class JMSListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            System.out.println("JMSListener:" + objectMessage.getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

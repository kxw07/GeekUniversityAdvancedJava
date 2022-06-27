package practice.spring.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

@Component
public class JMSReceiveService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void receive() {
        ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive("TEST.FOO");
        try {
            System.out.println("JMSReceiveService:" + objectMessage.getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

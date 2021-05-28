package week5.practice.spring.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import week5.practice.spring.aop.obj.Student;

import javax.jms.Message;
import javax.jms.Session;

@Component
public class JMSSendService {
    @Autowired
    JmsTemplate jmsTemplate;

    public void send(final Student student) {

        jmsTemplate.send("TEST.FOO", new MessageCreator() {
            @SneakyThrows
            @Override
            public Message createMessage(Session session) {
                return session.createObjectMessage(new ObjectMapper().writeValueAsString(student));
            }
        });
    }
}

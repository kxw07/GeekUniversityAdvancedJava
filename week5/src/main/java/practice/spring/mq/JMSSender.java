package practice.spring.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import practice.spring.aop.obj.Student;

import java.time.Instant;

public class JMSSender {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(Instant.now().toEpochMilli());
        student.setName("Tom");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.prac.spring.mq.xml");

        JMSSendService jmsSendService = (JMSSendService) applicationContext.getBean("JMSSendService");
        jmsSendService.send(student);


        JMSReceiveService jmsReceiveService = (JMSReceiveService) applicationContext.getBean("JMSReceiveService");
        jmsReceiveService.receive();
    }
}

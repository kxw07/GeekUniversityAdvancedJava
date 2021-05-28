package week5.practice.spring.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week5.practice.spring.aop.obj.Student;

public class JMSSender {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(456);
        student.setName("Tom");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.spring.mq.xml");

        JMSSendService jmsSendService = (JMSSendService) applicationContext.getBean("JMSSendService");
        jmsSendService.send(student);
    }
}

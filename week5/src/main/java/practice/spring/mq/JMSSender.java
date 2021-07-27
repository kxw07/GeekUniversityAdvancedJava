package practice.spring.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import practice.spring.aop.obj.Student;

public class JMSSender {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(456);
        student.setName("Tom");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.prac.spring.mq.xml");

        JMSSendService jmsSendService = (JMSSendService) applicationContext.getBean("JMSSendService");
        jmsSendService.send(student);
    }
}

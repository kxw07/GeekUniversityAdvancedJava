package week5.practice.spring_app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week5.practice.spring_app.obj.Student;

public class SpringApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.spring.app.xml");
        Student student001 = (Student) applicationContext.getBean("student001");
        System.out.println(student001);
    }
}

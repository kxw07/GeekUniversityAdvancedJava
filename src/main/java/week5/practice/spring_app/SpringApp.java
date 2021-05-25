package week5.practice.spring_app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week5.practice.spring_app.iml.ISchool;
import week5.practice.spring_app.obj.Klass;
import week5.practice.spring_app.obj.School;
import week5.practice.spring_app.obj.Student;

public class SpringApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.spring.app.xml");
        Student student001 = (Student) applicationContext.getBean("student001");
        System.out.println(student001);

        Student student002 = (Student) applicationContext.getBean("student002");
        System.out.println(student002);

        Klass class01 = (Klass) applicationContext.getBean("class01");
        System.out.println(class01);
        class01.dong();

        ISchool school = applicationContext.getBean(ISchool.class);
        System.out.println(school);
        school.ding();
        System.out.println("School real class: " + school.getClass());


    }
}

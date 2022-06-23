package practice.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import practice.spring.aop.iml.ISchool;
import practice.spring.aop.obj.Klass;
import practice.spring.aop.obj.Student;

public class SpringApp {
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.prac.spring.aop.xml");
        applicationContext.registerShutdownHook();

        System.out.println("=======================");
        System.out.println("(Student) applicationContext.getBean(\"student001\")");
        Student student001 = (Student) applicationContext.getBean("student001");
        System.out.println(student001);

        System.out.println("=======================");
        System.out.println("(Student) applicationContext.getBean(\"student002\")");
        Student student002 = (Student) applicationContext.getBean("student002");
        System.out.println(student002);

        System.out.println("=======================");
        System.out.println("(Klass) applicationContext.getBean(\"class01\")");
        Klass class01 = (Klass) applicationContext.getBean("class01");
        System.out.println(class01);
        class01.dong();
        System.out.println("class01 real class: " + class01.getClass());
        System.out.println("Is class01_class a Klass: " + (class01 instanceof Klass));

        System.out.println("=======================");
        // Note: use xml to config aop, the around behavior is different from annotation setting.
        // The config setting's after-returning will in the end of aop.
        System.out.println("applicationContext.getBean(ISchool.class)");
        ISchool school = applicationContext.getBean(ISchool.class);
        System.out.println(school);
        school.ding();
        System.out.println("ISchool real class: " + school.getClass());

        System.out.println("=======================");;
        System.out.println("applicationContext.getBean(Klass.class)");
        Klass class01_class = applicationContext.getBean(Klass.class);
        System.out.println(class01_class);
        class01_class.dong();
        System.out.println("class01_class real class: " + class01_class.getClass());
        System.out.println("Is class01_class a Klass: " + (class01_class instanceof Klass));

    }
}

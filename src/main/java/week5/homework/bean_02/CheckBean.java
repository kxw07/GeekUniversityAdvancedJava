package week5.homework.bean_02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import week5.homework.bean_02.obj.Bike;
import week5.homework.bean_02.obj.Car;
import week5.homework.bean_02.obj.Moto;

public class CheckBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.week5.homework.spring.bean_02.xml");

        Bike bike01 = (Bike) applicationContext.getBean("bike01");
        System.out.println("bike01: " + bike01);

        Car car01 = applicationContext.getBean(Car.class);
        System.out.println("car01: " + car01);

        Moto moto01 = applicationContext.getBean(Moto.class);
        System.out.println("moto01: " + moto01);
    }
}

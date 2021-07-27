package practice.spring.aop.obj;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import practice.spring.aop.iml.ISchool;

import javax.annotation.Resource;

@Data
public class School implements ISchool {

    @Autowired(required = true)
    Klass class01;

    @Resource(name = "student002")
    Student student002;

    @Override
    public void ding() {
        System.out.println("School has class: " + this.class01);
        System.out.println("How many students in class01: " + this.class01.getStudents().size());
        System.out.println("In class01, one student is: " + this.student002);
    }
}

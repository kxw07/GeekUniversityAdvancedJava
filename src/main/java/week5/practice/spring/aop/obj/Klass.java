package week5.practice.spring.aop.obj;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Klass {
    private List<Student> students;

    public void dong() {
        System.out.println("Klass dong:" + this.getStudents());
    }
}

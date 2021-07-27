package practice.spring.aop.obj;

import lombok.Data;

import java.util.List;

@Data
public class Klass {
    private List<Student> students;

    public void dong() {
        System.out.println("Klass dong:" + this.getStudents());
    }
}

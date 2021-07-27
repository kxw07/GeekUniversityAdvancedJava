package homework.starter_08;

import kxw07.github.starter.sch.School;
import kxw07.github.starter.stu.Student;
import kxw07.github.starter.kla.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckService {

    @Autowired
    private Student student;

    @Autowired
    private Klass klass;

    @Autowired
    private School school;

    @Autowired
    private Environment env;

    @GetMapping(value = "env")
    public void check(){
        System.out.println(env.getProperty("kxw07.example.host"));
    }

    @GetMapping(value = "stu")
    public void stu(){
        System.out.println(this.student);
    }

    @GetMapping(value = "kla")
    public void kla(){
        System.out.println(this.klass);
    }

    @GetMapping(value = "sch")
    public void sch(){
        System.out.println(this.school);
    }
}

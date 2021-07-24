package others.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticeSpringApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(PracticeSpringApplication.class);
        springApplication.run();
    }
}

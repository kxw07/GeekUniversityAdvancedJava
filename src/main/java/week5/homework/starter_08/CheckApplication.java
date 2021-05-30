package week5.homework.starter_08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.yml")
public class CheckApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CheckApplication.class);
        app.run();
    }
}

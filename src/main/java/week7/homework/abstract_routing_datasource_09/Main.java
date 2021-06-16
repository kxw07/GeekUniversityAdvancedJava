package week7.homework.abstract_routing_datasource_09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.run();
    }
}

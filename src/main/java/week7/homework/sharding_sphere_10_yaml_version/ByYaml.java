package week7.homework.sharding_sphere_10_yaml_version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// spring active profiles: week7-10
@SpringBootApplication
public class ByYaml {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ByYaml.class);
        springApplication.run();
    }
}

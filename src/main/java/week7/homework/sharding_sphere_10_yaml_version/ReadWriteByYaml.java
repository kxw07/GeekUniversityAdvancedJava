package week7.homework.sharding_sphere_10_yaml_version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// spring active profiles: week7-10
@SpringBootApplication
public class ReadWriteByYaml {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ReadWriteByYaml.class);
        springApplication.run();
    }
}

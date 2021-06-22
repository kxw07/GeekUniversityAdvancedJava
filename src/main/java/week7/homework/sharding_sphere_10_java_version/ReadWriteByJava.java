package week7.homework.sharding_sphere_10_java_version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadWriteByJava {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ReadWriteByJava.class);
        springApplication.run();
    }
}

// should exclude shardingsphere start dependency from pom.xml

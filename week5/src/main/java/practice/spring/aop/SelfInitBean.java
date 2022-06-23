package practice.spring.aop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelfInitBean {

    @Bean
    public void init() {
        System.out.println("Bean init...");
    }
}

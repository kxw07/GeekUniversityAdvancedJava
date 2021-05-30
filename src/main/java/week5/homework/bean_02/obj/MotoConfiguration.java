package week5.homework.bean_02.obj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MotoConfiguration {

    @Bean
    public Moto moto() {
        return new Moto();
    }
}

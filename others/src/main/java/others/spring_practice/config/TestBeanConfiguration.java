package others.spring_practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {

    @Bean(name = "namedBean")
    public TestClassA namedBean() {
        TestClassA testClassA = new TestClassA();
        testClassA.setValue("namedBean");

        return testClassA;
    }

    @Bean(name = "defaultBean")
    public TestClassA defaultBean() {
        TestClassA testClassA = new TestClassA();
        testClassA.setValue("defaultBean");

        return testClassA;
    }

    @Bean
    public TestClassB testClassB() {
        final TestClassB testClassB = new TestClassB();
        testClassB.setName("Kai");

        return testClassB;
    }
}

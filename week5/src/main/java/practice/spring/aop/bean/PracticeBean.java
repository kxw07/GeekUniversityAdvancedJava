package practice.spring.aop.bean;


import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Data
@Component
public class PracticeBean implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    private String beanName;

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        System.out.println("PracticeBean init...");
    }

    @Override
    public void destroy() {
        System.out.println("this.beanName:" + this.beanName);
        System.out.println("applicationContext.getBeanDefinitionNames():" + applicationContext.getBeanDefinitionNames());

        // should add applicationContext.registerShutdownHook();
        System.out.println("PracticeBean destroy...");
    }
}

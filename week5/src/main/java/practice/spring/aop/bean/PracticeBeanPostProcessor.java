package practice.spring.aop.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PracticeBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof PracticeBean) {
            PracticeBean practiceBean = (PracticeBean) bean;
            practiceBean.setBeanName(practiceBean.getBeanName() + System.currentTimeMillis());
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("postProcessAfterInitialization:" + beanName);

        return bean;
    }
}

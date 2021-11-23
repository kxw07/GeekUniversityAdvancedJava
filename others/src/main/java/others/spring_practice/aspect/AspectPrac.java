package others.spring_practice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectPrac {

    @Pointcut(value = "execution (public String others.spring_practice.aspect.AspectController.get(String))")
    public void pointcut() {

    }

    @Around("pointcut()&& args(name)")
    public String around(ProceedingJoinPoint pjp, String name) throws Throwable {
        System.out.println("around name:" + name);
        System.out.println("aspect around before proceed");
        Object result = pjp.proceed();
        System.out.println("result:" + result);
        System.out.println("aspect around after proceed");
        return "ASPECT RETURN";
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("aspect before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("aspect after");
    }
}

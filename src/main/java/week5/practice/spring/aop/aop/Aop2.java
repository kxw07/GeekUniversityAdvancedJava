package week5.practice.spring.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Aop2 {

    @Pointcut(value = "execution(* week5.practice.spring.aop.obj.*.*dong(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
        System.out.println("Aop2 begin dong...");
    }

    @AfterReturning(value = "point()")
    public void after() {
        System.out.println("Aop2 finish dong...");
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aop2 around begin dong...");

        joinPoint.proceed();

        System.out.println("Aop2 around after dong...");
    }

}

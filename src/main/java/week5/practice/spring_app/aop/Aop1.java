package week5.practice.spring_app.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aop1 {

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aop1 around begin ding...");

        joinPoint.proceed();

        System.out.println("Aop1 around after ding...");
    }

    public void startTransaction() {
        System.out.println("Aop1 before ding...");
    }

    public void commitTransaction() {
        System.out.println("Aop1 finish ding...");
    }
}

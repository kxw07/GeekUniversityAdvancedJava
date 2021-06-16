package week7.homework.abstract_routing_datasource_09.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    /**
     * 切點: 所有配置 ChangeDataSource 註解的方法
     */
    @Pointcut("@annotation(week7.homework.abstract_routing_datasource_09.datasource.ChangeDataSource)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ChangeDataSource ds = method.getAnnotation(ChangeDataSource.class);

        // 通過判斷 @ChangeDataSource註解 中的值來判斷當前方法應用哪個資料來源
        DynamicDataSource.setDataSource(ds.value());
        System.out.println("current datasource is : " + ds.value());
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            System.out.println("clean datasource");
        }
    }
}

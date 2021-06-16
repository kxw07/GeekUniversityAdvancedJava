package week7.homework.abstract_routing_datasource_09.datasource;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeDataSource {
    String value() default DataSourceName.WRITE;
}


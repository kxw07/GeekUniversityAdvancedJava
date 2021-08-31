package others.practice_spring.config;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

public class TestClassB {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    @Value("${java.home}")
    public void setValue(String value) {
        this.value = value;
    }
}

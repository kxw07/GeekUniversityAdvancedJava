package others.gson_practice;

import lombok.ToString;

@ToString
public class TestObj {
    private String name;
    private String value;

    public TestObj() {

    }

    public TestObj(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("call setName");
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        System.out.println("call setValue");
        this.value = value;
    }
}

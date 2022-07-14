package others.kryo_practice;

import lombok.Data;

@Data
public class TestObject implements java.io.Serializable {
    private static final long serialVersionUID = 20220714001L;

    private String name;

    private int field;
}

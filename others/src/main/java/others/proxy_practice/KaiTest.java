package others.proxy_practice;

public interface KaiTest {
    default String get() {
        return "default";
    };
}

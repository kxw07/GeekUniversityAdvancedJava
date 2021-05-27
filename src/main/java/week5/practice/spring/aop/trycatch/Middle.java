package week5.practice.spring.aop.trycatch;

public class Middle {
    private Bottom bottom;

    public Middle(Bottom bottom) {
        this.bottom = bottom;
    }

    public void get() {
        try {
            System.out.println("Middle in try");
            this.bottom.get();
        } catch (Exception e) {
            System.out.println("Middle in catch");
        } finally {
            System.out.println("Middle in finally");
        }
    }
}

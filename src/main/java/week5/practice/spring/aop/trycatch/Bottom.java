package week5.practice.spring.aop.trycatch;

public class Bottom {
    public void get() throws Exception {
        try {
            System.out.println("Bottom in try");
            throw new Exception("Bottom in try throw");
        } catch (Exception e) {
            System.out.println("Bottom in catch");
            throw new Exception("Bottom in catch throw");
        } finally {
            System.out.println("Bottom in finally");
        }
    }
}

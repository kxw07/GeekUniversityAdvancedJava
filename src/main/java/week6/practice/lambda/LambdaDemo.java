package week6.practice.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        TwoSum ts = new TwoSum<Integer>() {
            @Override
            public Integer operate(int a, int b) {
                return a + b;
            }
        };

        System.out.println(ts.operate(3, 5));

        TwoSum ts2 = (a, b) -> a + b;
        System.out.println(ts2.operate(1, 2));

        Dummy d = new Dummy() {
            @Override
            public void print(String s) {
                System.out.println(s);
            }
        };

        d.print("dummy test");

        Dummy d2 = (s) -> System.out.println(s);
        d2.print("dummy test2");

        Dummy d3 = System.out::println;
        d3.print("dummy test3");
    }

    interface TwoSum<T> {
        T operate(int a, int b);
    }

    interface Dummy {
        void print(String s);
    }
}

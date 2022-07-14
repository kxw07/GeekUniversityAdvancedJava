package practice.lambda;

public class LambdaDemo {
    public static void main(String[] args) {
        MathOperation add = new MathOperation<Integer>() {
            @Override
            public Integer operate(int a, int b) {
                return a + b;
            }
        };
        System.out.println(add.operate(3, 5));

        MathOperation subtract = (a, b) -> a - b;
        MathOperation divide = (a, b) -> a / b;
        MathOperation multiple = (a, b) -> a * b;

        final LambdaDemo lambdaDemo = new LambdaDemo();
        System.out.println("3 + 6 = " + lambdaDemo.operate(add, 3, 6));
        System.out.println("3 - 6 = " + lambdaDemo.operate(subtract, 3, 6));
        System.out.println("3 / 6 = " + lambdaDemo.operate(divide, 3, 6));
        System.out.println("3 * 6 = " + lambdaDemo.operate(multiple, 3, 6));
        System.out.println("3 * 6 = " + lambdaDemo.operate((a, b) -> a * 6, 3, 6));


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

    interface MathOperation<T> {
        T operate(int a, int b);
    }

    interface Dummy {
        void print(String s);
    }

    private <T> T operate(MathOperation<T> m, int a, int b) {
        return m.operate(a, b);
    }
}

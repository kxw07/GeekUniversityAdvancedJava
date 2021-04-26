package week1;

public class HelloByteCode {

    public static void main(String[] args) {
        HelloByteCode helloByteCode = new HelloByteCode();
    }

    public void checkForLoopByteCode() {
        int[] numberArray = {1, 2, 3};
        int sum = 0;
        for (int number : numberArray) {
            sum += number;
        }
    }
}

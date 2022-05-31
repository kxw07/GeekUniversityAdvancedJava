package others.java8_practice.predicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ApplePrinter {
    public static void main(String[] args) {
        final Apple apple1 = new Apple(Color.RED, 10);
        final Apple apple2 = new Apple(Color.RED, 20);
        final Apple apple3 = new Apple(Color.RED, 30);
        final Apple apple4 = new Apple(Color.RED, 40);
        final Apple apple5 = new Apple(Color.RED, 50);
        final Apple apple6 = new Apple(Color.GREEN, 10);
        final Apple apple7 = new Apple(Color.GREEN, 20);
        final Apple apple8 = new Apple(Color.GREEN, 30);
        final Apple apple9 = new Apple(Color.GREEN, 40);
        final Apple apple10 = new Apple(Color.GREEN, 50);

        final List<Apple> inventory = Arrays.asList(apple1, apple2, apple3, apple4, apple5, apple6, apple7, apple8, apple9, apple10);
        printApple(inventory, new AppleWeightFormatter());
        printApple(inventory, new AppleColorFormatter());

        List<Apple> redApples = filter(inventory, (Apple apple)-> apple.getColor().equals(Color.RED));

        // work, because reference type's value save in heap
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a", 1);
        Runnable r1 = () -> System.out.println(hashMap.get("a"));
        hashMap.put("b", 2);

        // not work, because value type's value save in stack
        // int port = 8080;
        // port = 18080;
        // Runnable r2 = () -> System.out.println(port);
    }

    public static void printApple(List<Apple> apples, AppleFormatter appleFormatter) {
        apples.forEach(apple -> System.out.println(appleFormatter.print(apple)));
    }

    public interface AppleFormatter {
        String print(Apple apple);
    }

    public static class AppleWeightFormatter implements AppleFormatter {

        @Override
        public String print(Apple apple) {
            return apple.getWeight() > 30 ? "A Heavy Apple" : "A Light Apple";
        }
    }

    public static class AppleColorFormatter implements AppleFormatter {

        @Override
        public String print(Apple apple) {
            return "A " + apple.getColor() + " Apple";
        }
    }

//    public interface Predicate<T> {
//        boolean test(T t);
//    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new java.util.ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

}

import java.util.function.Predicate;

public class bai3 {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("4 is even: " + isEven.test(4));
        System.out.println("7 is even: " + isEven.test(7));
    }
}


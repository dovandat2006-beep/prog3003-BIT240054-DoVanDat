import java.util.Arrays;

public class bai6 {
    public static void main(String[] args) {
        int sum = Arrays.asList(1, 2, 3, 4, 5, 6).stream().filter(n -> n % 2 == 0).mapToInt(n -> n * n).sum();
        System.out.println(sum);
    }
}


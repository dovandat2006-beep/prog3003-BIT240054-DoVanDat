import java.util.Arrays;
import java.util.List;

public class bai2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Hanoi", "Ho Chi Minh", "Da Nang", "Hue");
        java.util.Collections.sort(cities, (a, b) -> a.length() - b.length());
        cities.forEach(System.out::println);
    }
}


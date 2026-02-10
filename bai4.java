import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class bai4 {
    public static void main(String[] args) {
        List<String> currencies = Arrays.asList("$10", "$20", "$50");
        Function<String, Integer> parseCurrency = s -> Integer.parseInt(s.replace("$", ""));
        List<Integer> numbers = currencies.stream().map(parseCurrency).collect(Collectors.toList());
        System.out.println(numbers);
    }
}


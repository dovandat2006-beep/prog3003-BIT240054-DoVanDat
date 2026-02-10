import java.util.function.Supplier;
import java.util.function.Consumer;

public class bai5 {
    public static void main(String[] args) {
        Supplier<Double> randomNumber = () -> Math.random() * 100;
        Consumer<Double> printer = n -> System.out.println("Số may mắn: " + n);
        printer.accept(randomNumber.get());
    }
}


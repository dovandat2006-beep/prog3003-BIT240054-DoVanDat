import java.util.Scanner;

public class Bai4TemperatureConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap nhiet do Celsius: ");

        if (scanner.hasNextDouble()) {
            double celsius = scanner.nextDouble();
            double fahrenheit = celsius * 9 / 5 + 32;
            System.out.printf("Ket qua: %.2f do C = %.2f do F%n", celsius, fahrenheit);
        } else {
            System.out.println("Vui long nhap mot gia tri so hop le.");
        }

        scanner.close();
    }
}

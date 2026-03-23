public class Bai1 {
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = {10, 20, 30, 40};
        String[] words = {"An", "Binh", "Chi", "Dung"};

        System.out.println("===== BAI 1: GENERIC METHOD =====");
        System.out.println("Mang Integer:");
        printArray(numbers);

        System.out.println("Mang String:");
        printArray(words);
    }
}

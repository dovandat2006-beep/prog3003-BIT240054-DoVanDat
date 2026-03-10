import java.util.TreeSet;

public class Bai1TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> names = new TreeSet<>();
        names.add("John");
        names.add("Alice");
        names.add("Zack");
        names.add("Bob");

        System.out.println("Danh sach trong TreeSet: " + names);
        System.out.println("Phan tu dau tien: " + names.first());
        System.out.println("Phan tu cuoi cung: " + names.last());
    }
}

import java.util.HashMap;
import java.util.Map;

public class Bai1HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> employees = new HashMap<>();

        employees.put(101, "Anna");
        employees.put(102, "Peter");
        employees.put(103, "Mary");

        String employee102 = employees.get(102);
        System.out.println("Nhan vien co ID 102: " + employee102);

        if (!employees.containsKey(105)) {
            employees.put(105, "Unknown");
            System.out.println("Da them nhan vien co ID 105: Unknown");
        }

        System.out.println("Danh sach nhan vien: " + employees);
    }
}

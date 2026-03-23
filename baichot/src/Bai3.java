import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bai3 {
    static class Employee {
        private final int id;
        private final String name;
        private final double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Lan", 950),
            new Employee(2, "Binh", 1400),
            new Employee(3, "An", 2200),
            new Employee(4, "Cuong", 1100)
        );

        List<String> highSalaryNames = employees.stream()
            .filter(employee -> employee.getSalary() > 1000)
            .map(Employee::getName)
            .sorted()
            .collect(Collectors.toList());

        System.out.println("===== BAI 3: OBJECT & COLLECTORS =====");
        System.out.println("Danh sach ten nhan vien co luong > 1000:");
        System.out.println(highSalaryNames);
    }
}

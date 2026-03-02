package bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private final List<Student> students = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new StudentManagementApp().run();
    }

    private void run() {
        while (true) {
            printMenu();
            System.out.print("Chon chuc nang: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addStudent();
                    break;
                case "2":
                    showAllStudents();
                    break;
                case "3":
                    findByName();
                    break;
                case "4":
                    deleteByMssv();
                    break;
                case "0":
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== MENU QUAN LY SINH VIEN =====");
        System.out.println("1. Them sinh vien");
        System.out.println("2. Hien thi danh sach sinh vien");
        System.out.println("3. Tim kiem sinh vien theo ten");
        System.out.println("4. Xoa sinh vien theo MSSV");
        System.out.println("0. Thoat");
    }

    private void addStudent() {
        System.out.print("Nhap MSSV: ");
        String mssv = scanner.nextLine().trim();
        if (mssv.isEmpty()) {
            System.out.println("MSSV khong duoc de trong.");
            return;
        }
        for (Student s : students) {
            if (s.getMssv().equalsIgnoreCase(mssv)) {
                System.out.println("MSSV da ton tai.");
                return;
            }
        }

        System.out.print("Nhap ten: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ten khong duoc de trong.");
            return;
        }

        System.out.print("Nhap GPA: ");
        String gpaText = scanner.nextLine().trim();
        try {
            double gpa = Double.parseDouble(gpaText);
            students.add(new Student(mssv, name, gpa));
            System.out.println("Da them sinh vien.");
        } catch (NumberFormatException e) {
            System.out.println("GPA khong hop le.");
        }
    }

    private void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("\nDanh sach sinh vien:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void findByName() {
        System.out.print("Nhap ten can tim: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        if (keyword.isEmpty()) {
            System.out.println("Ten tim kiem khong duoc de trong.");
            return;
        }

        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay sinh vien nao.");
        }
    }

    private void deleteByMssv() {
        System.out.print("Nhap MSSV can xoa: ");
        String mssv = scanner.nextLine().trim();
        boolean removed = students.removeIf(s -> s.getMssv().equalsIgnoreCase(mssv));
        if (removed) {
            System.out.println("Da xoa sinh vien co MSSV: " + mssv);
        } else {
            System.out.println("Khong tim thay MSSV: " + mssv);
        }
    }
}

class Student {
    private final String mssv;
    private final String name;
    private final double gpa;

    public Student(String mssv, String name, double gpa) {
        this.mssv = mssv;
        this.name = name;
        this.gpa = gpa;
    }

    public String getMssv() {
        return mssv;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("MSSV=%s, Ten=%s, GPA=%.2f", mssv, name, gpa);
    }
}

package com.mycompany.bai1;

import java.util.ArrayList;
import java.util.List;

public class Bai1 {
    public static class Student {
        private String id;
        private String name;
        private double gpa;

        public Student(String id, String name, double gpa) {
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", gpa=" + gpa +
                    '}';
        }
    }
    public static class StudentManager<T> {
        private List<T> data;

        public StudentManager() {
            data = new ArrayList<>();
        }

        public void add(T item) {
            data.add(item);
        }

        public List<T> getAll() {
            return data;
        }
    }
    public static void main(String[] args) {

        StudentManager<Student> manager = new StudentManager<>();

        manager.add(new Student("SV01", "Trang Dang Khoi", 7.8));   // gpa = double
        manager.add(new Student("SV02", "Nguyen Doan Huy", 9.8));
        manager.add(new Student("SV03", "Do Van Dat", 10.0));

        for (Student s : manager.getAll()) {
            System.out.println(s);
        }
    }
}
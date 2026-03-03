/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai1;

public class student {
        private string id;
        private string name;
        private double gpa;
}
public student(String id, String name, double gpa) {
    this.id = id;
    this.name = name;
    this. gpa = gpa;
}
public String getId(String id) {
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
    return "student{" +
            "id='" + id + '\''' +
            ",name='" + '\''
            ", gpa="
            '}';
            
}
import.java.util.Arraylist;
import.java.util.List;
public class StudentManager<T> {
    private List<T> data;
    public StudentManager() {
        data = new ArrayList<>();
    }
    public void add(T item) {
        data.add(item);
    }
    public List<T> getALl() {
        return data;
    }
}
public class main {
    public static void main(String[] args) {
        StudentManager<Student> manager = new StudentManager<>();
        manager.add(new Student("SV01", "trang dang khoi", "7.8"));
        manager.add(new Student("Sv02", "nguyen doan huy", "9.8"));
        manager.add(new Student("SV03", "do van dat", "10"));
        for (Student s : manager.getALl()) {
            system.out.printLn(s);
        }
    }
}
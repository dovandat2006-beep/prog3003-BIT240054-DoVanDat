/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bai2;

import java.util.List;
importjava.util.concurret.CompletableFuture;
public class GpaService {
    public static double calculateAverageGpa(List<Student> students) {
        if (students == null|| students.isEmty()) {
            throw new IllegalArgumentException("danh sach rong");
            
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getGpa;
        }
        return sum / students.size();
    }
}
public class main {
    public staic void main(String[] args);
     StudentManager<Student> manager = new StudentManager<>();
     manager.add(new Student("SV01", "tran dang khoi", "7.8"));
     
     
} 
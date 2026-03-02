package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateExample {
    public static void main(String[] args) {
        // cấu hình programmatic thay vì hibernate.cfg.xml
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Product.class);
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3307/javafx_demo?useSSL=false&serverTimezone=UTC");
        cfg.setProperty("hibernate.connection.username", "root");
        cfg.setProperty("hibernate.connection.password", "123456");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update"); // chỉ dùng cho mục đích demo

        SessionFactory sf = cfg.buildSessionFactory();

        // Bài 3: lưu product mới
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Product laptop = new Product("Laptop", 1500);
            session.save(laptop);
            session.getTransaction().commit();
        }

        // Bài 4: truy vấn HQL
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List<Product> expensive = session
                    .createQuery("FROM Product p WHERE p.price > 1000", Product.class)
                    .getResultList();
            for (Product p : expensive) {
                System.out.println(p);
            }
            session.getTransaction().commit();
        }

        sf.close();
    }
}

package com.example;

import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) throws Exception {
        // Bài 1: SELECT * FROM users và in ra
        try (Connection conn = DBUtil.getConnection()) {
            // adjust columns to match schema (name instead of username, no email column)
            String selectSql = "SELECT id, name FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.printf("%d - %s%n", id, name);
                }
            }

            // Bài 2: xóa user theo id
            int deleteId = 5; // thay đổi theo nhu cầu hoặc truyền từ args
            deleteUser(conn, deleteId);
        }
    }

    public static void deleteUser(Connection conn, int id) throws SQLException {
        String deleteSql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(deleteSql)) {
            pst.setInt(1, id);
            int count = pst.executeUpdate();
            if (count == 0) {
                System.out.println("Không tìm thấy user để xóa");
            } else {
                System.out.println("Đã xóa user có id=" + id);
            }
        }
    }
}

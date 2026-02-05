// ============================================================
// BÀI 1: SINGLETON PATTERN - DatabaseConnection
// ============================================================

class DatabaseConnection {
    // Bước 2: Tạo biến static để lưu instance duy nhất
    private static DatabaseConnection instance;
    
    // Bước 1: Constructor là private
    private DatabaseConnection() {
        System.out.println("DatabaseConnection: Tạo kết nối CSDL mới...");
    }
    
    // Bước 3: Phương thức public static getInstance()
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("DatabaseConnection: Đang kết nối đến CSDL...");
    }
}

public class Bai1_Singleton {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("BÀI 1: SINGLETON PATTERN - DatabaseConnection");
        System.out.println("============================================================");
        
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        DatabaseConnection conn2 = DatabaseConnection.getInstance();
        System.out.println("conn1 == conn2? " + (conn1 == conn2));
        conn1.connect();
        
        System.out.println("============================================================");
        System.out.println("BÀI 1 HOÀN THÀNH!");
        System.out.println("============================================================");
    }
}


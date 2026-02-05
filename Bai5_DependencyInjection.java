// ============================================================
// BÀI 5: DEPENDENCY INJECTION - Message Service
// ============================================================

// Bước 1: Tạo interface MessageService
interface MessageService {
    void sendMessage(String message);
}

// Bước 2: Tạo 2 lớp implement từ interface
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("EmailService: Đang gửi Email: \"" + message + "\"");
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("SMSService: Đang gửi SMS: \"" + message + "\"");
    }
}

// Bước 3: Tạo lớp Notification sử dụng Setter Injection
class Notification {
    private MessageService messageService;
    
    // Setter Injection
    public void setMessageService(MessageService service) {
        this.messageService = service;
    }
    
    public void sendNotification(String message) {
        if (messageService == null) {
            System.out.println("Notification: Chưa cấu hình dịch vụ gửi tin nhắn!");
            return;
        }
        messageService.sendMessage(message);
    }
}

public class Bai5_DependencyInjection {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("BÀI 5: DEPENDENCY INJECTION - Message Service");
        System.out.println("============================================================");
        
        Notification notification = new Notification();
        
        // Gửi qua EmailService
        System.out.println("--- Gửi qua EmailService ---");
        notification.setMessageService(new EmailService());
        notification.sendNotification("Chào mừng bạn đến với hệ thống!");
        
        // Gửi qua SMSService
        System.out.println("\n--- Gửi qua SMSService ---");
        notification.setMessageService(new SMSService());
        notification.sendNotification("Mã xác nhận của bạn là: 123456");
        
        System.out.println("============================================================");
        System.out.println("BÀI 5 HOÀN THÀNH!");
        System.out.println("============================================================");
    }
}


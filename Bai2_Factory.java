// ============================================================
// BÀI 2: FACTORY PATTERN - Notification System
// ============================================================

// Bước 1: Tạo interface Notification
interface Notification {
    void notifyUser();
}

// Bước 2: Tạo các class implement interface
class SMSNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("SMSNotification: Gửi thông báo qua SMS!");
    }
}

class EmailNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("EmailNotification: Gửi thông báo qua Email!");
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser() {
        System.out.println("PushNotification: Gửi thông báo qua Push Notification!");
    }
}

// Bước 3: Tạo NotificationFactory
class NotificationFactory {
    public Notification createNotification(String channel) {
        switch (channel.toUpperCase()) {
            case "SMS":
                return new SMSNotification();
            case "EMAIL":
                return new EmailNotification();
            case "PUSH":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Kênh thông báo không hợp lệ: " + channel);
        }
    }
}

public class Bai2_Factory {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("BÀI 2: FACTORY PATTERN - Notification System");
        System.out.println("============================================================");
        
        NotificationFactory factory = new NotificationFactory();
        
        Notification sms = factory.createNotification("SMS");
        sms.notifyUser();
        
        Notification email = factory.createNotification("EMAIL");
        email.notifyUser();
        
        Notification push = factory.createNotification("PUSH");
        push.notifyUser();
        
        System.out.println("============================================================");
        System.out.println("BÀI 2 HOÀN THÀNH!");
        System.out.println("============================================================");
    }
}


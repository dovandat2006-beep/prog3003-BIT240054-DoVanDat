// ============================================================
// BÀI 4: STRATEGY PATTERN - Payment System
// ============================================================

// Bước 1: Tạo interface PaymentStrategy
interface PaymentStrategy {
    void pay(int amount);
}

// Bước 2: Tạo 2 class implement interface
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("CreditCardPayment: Thanh toán " + amount + " VNĐ bằng thẻ " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("PayPalPayment: Thanh toán " + amount + " VNĐ qua PayPal (" + email + ")");
    }
}

// Bước 3: Tạo class ShoppingCart
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("ShoppingCart: Chưa chọn phương thức thanh toán!");
            return;
        }
        paymentStrategy.pay(amount);
        System.out.println("ShoppingCart: Thanh toán thành công!");
    }
}

public class Bai4_Strategy {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("BÀI 4: STRATEGY PATTERN - Payment System");
        System.out.println("============================================================");
        
        ShoppingCart cart = new ShoppingCart();
        
        // Thanh toán bằng CreditCard
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(150000);
        
        // Đổi sang PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(75000);
        
        System.out.println("============================================================");
        System.out.println("BÀI 4 HOÀN THÀNH!");
        System.out.println("============================================================");
    }
}


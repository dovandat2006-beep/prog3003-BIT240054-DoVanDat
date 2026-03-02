package bai2;

public class PaymentProcessingDemo {
    public static void main(String[] args) {
        PaymentFactory creditFactory = new CreditCardFactory();
        PaymentFactory paypalFactory = new PayPalFactory();
        PaymentFactory cashFactory = new CashFactory();

        Payment creditPayment = creditFactory.createPayment();
        Payment paypalPayment = paypalFactory.createPayment();
        Payment cashPayment = cashFactory.createPayment();

        creditPayment.process(120.50);
        paypalPayment.process(75.00);
        cashPayment.process(40.00);
    }
}

interface Payment {
    void process(double amount);
}

class CreditCardPayment implements Payment {
    @Override
    public void process(double amount) {
        System.out.printf("Thanh toan Credit Card: %.2f VND%n", amount);
    }
}

class PayPalPayment implements Payment {
    @Override
    public void process(double amount) {
        System.out.printf("Thanh toan PayPal: %.2f VND%n", amount);
    }
}

class CashPayment implements Payment {
    @Override
    public void process(double amount) {
        System.out.printf("Thanh toan tien mat: %.2f VND%n", amount);
    }
}

abstract class PaymentFactory {
    public abstract Payment createPayment();
}

class CreditCardFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}

class PayPalFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new PayPalPayment();
    }
}

class CashFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CashPayment();
    }
}

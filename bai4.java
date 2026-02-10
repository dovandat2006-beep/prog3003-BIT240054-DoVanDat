import java.util.concurrent.CompletableFuture;

// Bài 4: Hệ thống xử lý đơn hàng trực tuyến với CompletableFuture
public class bai4 {
    
    // Tác vụ 1: Kiểm tra tính khả dụng của sản phẩm
    public static CompletableFuture<String> checkProductAvailability() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Giả lập thời gian xử lý 2 giây
                return "✓ Đã kiểm tra tính khả dụng của sản phẩm";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "✗ Lỗi kiểm tra sản phẩm";
            }
        });
    }
    
    // Tác vụ 2: Thanh toán
    public static CompletableFuture<String> processPayment() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000); // Giả lập thời gian xử lý 3 giây
                return "✓ Đã hoàn tất thanh toán";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "✗ Lỗi thanh toán";
            }
        });
    }
    
    // Tác vụ 3: Vận chuyển đơn hàng
    public static CompletableFuture<String> shipOrder() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2500); // Giả lập thời gian xử lý 2.5 giây
                return "✓ Đã vận chuyển đơn hàng";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "✗ Lỗi vận chuyển";
            }
        });
    }
    
    public static void main(String[] args) {
        System.out.println("🛒 Bắt đầu xử lý đơn hàng...\n");
        
        long startTime = System.currentTimeMillis();
        
        // Thực hiện các tác vụ bất đồng bộ
        CompletableFuture<String> availabilityFuture = checkProductAvailability()
            .whenComplete((result, error) -> {
                System.out.println(result);
            });
        
        CompletableFuture<String> paymentFuture = processPayment()
            .whenComplete((result, error) -> {
                System.out.println(result);
            });
        
        CompletableFuture<String> shippingFuture = shipOrder()
            .whenComplete((result, error) -> {
                System.out.println(result);
            });
        
        // Kết hợp tất cả các tác vụ và chờ hoàn thành
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
            availabilityFuture, 
            paymentFuture, 
            shippingFuture
        );
        
        // Chờ tất cả hoàn thành và hiển thị kết quả cuối cùng
        allTasks.thenRun(() -> {
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime) / 1000;
            
            System.out.println("\n📦 Đơn hàng đã được xử lý thành công!");
            System.out.println("⏱️ Tổng thời gian: " + totalTime + " giây");
        }).join();
    }
}


import java.util.concurrent.CompletableFuture;

public class Bai3CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> validateCustomerFuture = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            String result = "Xac thuc khach hang thanh cong";
            System.out.println(result);
            return result;
        });

        CompletableFuture<String> printTicketFuture = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            String result = "Xuat ve xem phim thanh cong";
            System.out.println(result);
            return result;
        });

        CompletableFuture<String> systemDoneFuture = validateCustomerFuture.thenCombine(
                printTicketFuture,
                (validationResult, ticketResult) -> validationResult + " | " + ticketResult
        );

        System.out.println("He thong hoan tat: " + systemDoneFuture.join());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Tac vu bi gian doan", e);
        }
    }
}

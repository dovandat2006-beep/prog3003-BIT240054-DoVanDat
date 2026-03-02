package bai3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncBankingDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        processTransaction("CUST-01", 2000.0, 500.0, executor);
        processTransaction("INVALID", 2000.0, 500.0, executor);
        processTransaction("CUST-02", 300.0, 500.0, executor);

        executor.shutdown();
    }

    private static void processTransaction(
            String customerId,
            double currentBalance,
            double transferAmount,
            ExecutorService executor) {

        System.out.println("\nBat dau giao dich cho " + customerId);

        CompletableFuture<Void> flow = authenticate(customerId, executor)
                .thenCompose(authResult -> checkBalance(currentBalance, transferAmount, executor))
                .thenCompose(balanceResult -> transferMoney(customerId, transferAmount, executor))
                .thenAccept(result -> System.out.println("Ket qua: " + result))
                .handle((ok, ex) -> {
                    if (ex != null) {
                        Throwable cause = ex instanceof CompletionException ? ex.getCause() : ex;
                        System.out.println("Giao dich bi huy: " + cause.getMessage());
                    }
                    return null;
                });

        flow.join();
    }

    private static CompletableFuture<Boolean> authenticate(String customerId, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1);
            System.out.println("Dang xac thuc khach hang...");
            if ("INVALID".equalsIgnoreCase(customerId)) {
                throw new IllegalStateException("Xac thuc that bai.");
            }
            System.out.println("Xac thuc thanh cong.");
            return true;
        }, executor);
    }

    private static CompletableFuture<Boolean> checkBalance(
            double currentBalance,
            double transferAmount,
            ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(2);
            System.out.println("Dang kiem tra so du...");
            if (currentBalance < transferAmount) {
                throw new IllegalStateException("So du khong du.");
            }
            System.out.println("So du hop le.");
            return true;
        }, executor);
    }

    private static CompletableFuture<String> transferMoney(
            String customerId,
            double transferAmount,
            ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1);
            System.out.println("Dang chuyen tien...");
            return String.format("Da chuyen %.2f cho %s", transferAmount, customerId);
        }, executor);
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Tac vu bi gian doan.", e);
        }
    }
}

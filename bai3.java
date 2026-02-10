import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

// Bài 3: Sử dụng CompletableFuture để xử lý mảng
public class bai3 {
    public static void main(String[] args) {
        // B1. Cung cấp một array bất kỳ bất đồng bộ
        CompletableFuture<int[]> futureArray = CompletableFuture.supplyAsync(() -> {
            return new int[]{1, 2, 5, 3, 100};
        });
        
        // B2. Nhận array, xử lý lấy số lẻ và sắp xếp tăng dần
        CompletableFuture<List<Integer>> futureOddSorted = futureArray.thenApply(arr -> {
            return Arrays.stream(arr)
                         .filter(x -> x % 2 != 0)  // Lọc số lẻ
                         .boxed()
                         .sorted()
                         .collect(Collectors.toList());
        });
        
        // B3. Nhận kết quả B2, chuyển thành chuỗi "Kết quả là: [...]"
        CompletableFuture<String> futureString = futureOddSorted.thenApply(oddList -> {
            return "Kết quả là: " + oddList;
        });
        
        // B4. In chuỗi cuối cùng ra màn hình
        String result = futureString.join();
        System.out.println(result);
    }
}


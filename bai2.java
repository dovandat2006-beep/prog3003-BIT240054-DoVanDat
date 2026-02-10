// Bài 2: Tạo 5 thread, mỗi thread in ra số từ 1-10 với tên thread
class NumberThread extends Thread {
    private int threadNumber;
    
    public NumberThread(int threadNumber) {
        this.threadNumber = threadNumber;
        setName("Thread-" + threadNumber);
    }
    
    @Override
    public void run() {
        System.out.print(getName() + ": ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

public class bai2 {
    public static void main(String[] args) {
        // Tạo và khởi chạy 5 thread
        for (int i = 1; i <= 5; i++) {
            NumberThread thread = new NumberThread(i);
            thread.start();
        }
    }
}


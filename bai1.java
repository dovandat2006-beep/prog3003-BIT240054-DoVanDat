public class bai1 {
    
}

// Bài 1: WorkerThread kế thừa từ Thread
class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread đang chạy...");
    }
}

// Bài 1: WorkerRunnable triển khai Runnable
class WorkerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable đang chạy...");
    }
}

// Class main để khởi chạy cả hai
class MainBai1 {
    public static void main(String[] args) {
        // Khởi chạy WorkerThread
        WorkerThread workerThread = new WorkerThread();
        workerThread.start();
        
        // Khởi chạy WorkerRunnable
        WorkerRunnable workerRunnable = new WorkerRunnable();
        Thread thread = new Thread(workerRunnable);
        thread.start();
    }
}


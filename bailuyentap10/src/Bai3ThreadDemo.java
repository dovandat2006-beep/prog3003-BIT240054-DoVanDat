class WorkerThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread đang chạy...");
    }
}

class WorkerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable đang chạy...");
    }
}

public class Bai3ThreadDemo {
    public static void main(String[] args) {
        WorkerThread workerThread = new WorkerThread();
        Thread runnableThread = new Thread(new WorkerRunnable());

        workerThread.start();
        runnableThread.start();

        try {
            workerThread.join();
            runnableThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Luong chinh bi gian doan.");
        }
    }
}

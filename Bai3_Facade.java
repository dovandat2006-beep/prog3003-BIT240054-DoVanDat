// ============================================================
// BÀI 3: FACADE PATTERN - Home Theater
// ============================================================

// Bước 1: Giả sử có các class con
class TV {
    public void turnOn() {
        System.out.println("TV: Bật tivi...");
    }
    
    public void turnOff() {
        System.out.println("TV: Tắt tivi...");
    }
}

class SoundSystem {
    public void turnOn() {
        System.out.println("SoundSystem: Bật loa...");
    }
    
    public void turnOff() {
        System.out.println("SoundSystem: Tắt loa...");
    }
}

class DVDPlayer {
    public void turnOn() {
        System.out.println("DVDPlayer: Bật đầu DVD...");
    }
    
    public void play() {
        System.out.println("DVDPlayer: Đang phát đĩa DVD...");
    }
    
    public void turnOff() {
        System.out.println("DVDPlayer: Tắt đầu DVD...");
    }
}

// Bước 2: Tạo class HomeTheaterFacade
class HomeTheaterFacade {
    private TV tv;
    private SoundSystem soundSystem;
    private DVDPlayer dvdPlayer;
    
    public HomeTheaterFacade() {
        this.tv = new TV();
        this.soundSystem = new SoundSystem();
        this.dvdPlayer = new DVDPlayer();
    }
    
    // Bước 3: Phương thức watchMovie()
    public void watchMovie() {
        System.out.println("\n=== Chuẩn bị xem phim ===");
        tv.turnOn();
        soundSystem.turnOn();
        dvdPlayer.turnOn();
        dvdPlayer.play();
        System.out.println("=== Bắt đầu xem phim! ===\n");
    }
    
    public void endMovie() {
        System.out.println("\n=== Kết thúc xem phim ===");
        dvdPlayer.turnOff();
        soundSystem.turnOff();
        tv.turnOff();
        System.out.println("=== Đã tắt tất cả thiết bị! ===\n");
    }
}

public class Bai3_Facade {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("BÀI 3: FACADE PATTERN - Home Theater");
        System.out.println("============================================================");
        
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie();
        homeTheater.endMovie();
        
        System.out.println("============================================================");
        System.out.println("BÀI 3 HOÀN THÀNH!");
        System.out.println("============================================================");
    }
}


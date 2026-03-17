interface MediaPlayer {
    void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
    void playVlc(String fileName);
}

class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Dang phat file VLC: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    private final AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if ("vlc".equalsIgnoreCase(audioType)) {
            this.advancedMediaPlayer = new VlcPlayer();
        } else {
            this.advancedMediaPlayer = null;
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if ("vlc".equalsIgnoreCase(audioType) && advancedMediaPlayer != null) {
            advancedMediaPlayer.playVlc(fileName);
        } else {
            System.out.println("Khong ho tro dinh dang: " + audioType);
        }
    }
}

class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Dang phat file MP3: " + fileName);
        } else if ("vlc".equalsIgnoreCase(audioType)) {
            MediaAdapter mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Khong ho tro dinh dang: " + audioType);
        }
    }
}

public class Bai2AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "baihat.mp3");
        audioPlayer.play("vlc", "phim.vlc");
        audioPlayer.play("mp4", "video.mp4");
    }
}

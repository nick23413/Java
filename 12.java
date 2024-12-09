// Интерфейс MediaPlayer
interface MediaPlayer {
    void play();
}

// Класс для воспроизведения MP3-файлов
class Mp3Player implements MediaPlayer {
    @Override
    public void play() {
        System.out.println("Playing MP3 file.");
    }
}

// Класс для воспроизведения WAV-файлов
class WavPlayer {
    public void playWav() {
        System.out.println("Playing WAV file.");
    }
}

// Адаптер для воспроизведения WAV-файлов через MP3-плеер
class WavToMp3Adapter implements MediaPlayer {
    private WavPlayer wavPlayer;

    public WavToMp3Adapter(WavPlayer wavPlayer) {
        this.wavPlayer = wavPlayer;
    }

    @Override
    public void play() {
        System.out.println("Adapter converts WAV to MP3 format.");
        wavPlayer.playWav();
    }
}

// Тестирование адаптера
public class AdapterExample {
    public static void main(String[] args) {
        // Создаем MP3-плеер и воспроизводим MP3-файл
        MediaPlayer mp3Player = new Mp3Player();
        mp3Player.play();

        // Создаем WAV-плеер и воспроизводим WAV-файл через адаптер
        WavPlayer wavPlayer = new WavPlayer();
        MediaPlayer wavAdapter = new WavToMp3Adapter(wavPlayer);
        wavAdapter.play();
    }
}


import javax.sound.sampled.*;
import java.net.URL;

public class AudioPlayer {
    private Clip clip;

    public AudioPlayer(String fileName) {
        try {
            // Mengambil lokasi file audio dari folder assets/audio
            URL soundURL = getClass().getResource(
                    "/assets/audio/" + fileName
            );

            // Memeriksa apakah file audio ditemukan
            if (soundURL == null) {
                System.out.println("Audio tidak ditemukan: " + fileName);
                return;
            }

            // Membaca file audio menjadi AudioInputStream
            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(soundURL);

            // Membuat Clip dan memuat data audio ke dalamnya
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        } catch (Exception e) {
            // Menampilkan pesan jika terjadi kesalahan saat memuat audio
            System.out.println("Gagal memuat audio: " + fileName);
            e.printStackTrace();
        }
    }

    // Memainkan audio satu kali dari awal
    public void play() {
        // Memeriksa apakah audio berhasil dimuat
        if (clip == null) return;

        // Menghentikan audio jika masih sedang dimainkan
        if (clip.isRunning()) {
            clip.stop();
        }

        // Mengatur posisi audio ke awal sebelum dimainkan
        clip.setFramePosition(0);
        clip.start();
    }

    // Memainkan audio secara berulang
    public void loop() {
        if (clip == null) return;

        // Menghentikan audio dan mengatur ulang posisinya
        clip.stop();
        clip.setFramePosition(0);

        // Memutar audio terus-menerus
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Menghentikan audio yang sedang dimainkan
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Menghentikan dan melepaskan resource audio
    public void close() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}

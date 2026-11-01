
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    private Player player;
    private View view;

    private Timer gameLoop;
    private Timer pipesCooldown;

    // Mengatur gravitasi dan karakteristik pipa
    private double gravity = 0.7;
    private int pipeWidth = 110;
    private int pipeHeight = 310;
    private int pipeVelocityX = -4;
    private int openingSpace = 150;

    // Menyimpan skor dan status permainan
    private int score = 0;
    private boolean gameStarted = false;
    private boolean gameOver = false;

    private ArrayList<Pipe> pipes;
    private Image pipeImg;

    // Menyimpan objek audio yang digunakan dalam permainan
    private AudioPlayer bgm;
    private AudioPlayer flapSound;
    private AudioPlayer scoreSound;
    private AudioPlayer gameOverSound;

    public Logic(Player player) {
        this.player = player;
        pipes = new ArrayList<>();

        // Memuat gambar pipa dari folder assets
        pipeImg = new ImageIcon(
                getClass().getResource("/assets/Lower pipe.png")
        ).getImage();

        // Memuat seluruh audio permainan
        bgm = new AudioPlayer("bgm.wav");
        flapSound = new AudioPlayer("flap.wav");
        scoreSound = new AudioPlayer("score.wav");
        gameOverSound = new AudioPlayer("gameover.wav");

        // Timer untuk menjalankan pergerakan permainan setiap 16 ms
        gameLoop = new Timer(16, this);

        // Timer untuk membuat pipa baru setiap 1,5 detik
        pipesCooldown = new Timer(1500, e -> placePipe());
    }

    // Mengembalikan objek player
    public Player getPlayer() {
        return player;
    }

    // Mengembalikan daftar seluruh pipa
    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // Mengembalikan nilai skor saat ini
    public int getScore() {
        return score;
    }

    // Memeriksa apakah permainan sudah dimulai
    public boolean isGameStarted() {
        return gameStarted;
    }

    // Memeriksa apakah permainan sudah berakhir
    public boolean isGameOver() {
        return gameOver;
    }

    // Menghubungkan Logic dengan View
    public void setView(View view) {
        this.view = view;
    }

    // Membuat sepasang pipa dengan jarak secara acak
    public void placePipe() {
        if (!gameStarted || gameOver) return;

        int pipeX = 360;

        // Menentukan posisi vertikal pipa secara acak
        int randomPipeY = (int) (
                -pipeHeight / 4 - Math.random() * (pipeHeight / 2)
        );

        // Membuat pipa bagian atas
        Pipe upperPipe = new Pipe(
                pipeX, randomPipeY,
                pipeWidth, pipeHeight, pipeImg
        );

        // Membuat pipa bagian bawah dengan jarak tertentu
        Pipe lowerPipe = new Pipe(
                pipeX,
                randomPipeY + pipeHeight + openingSpace,
                pipeWidth, pipeHeight, pipeImg
        );

        // Menambahkan kedua pipa ke dalam ArrayList
        pipes.add(upperPipe);
        pipes.add(lowerPipe);
    }

    // Menggerakkan player dan seluruh pipa
    public void move() {
        if (!gameStarted || gameOver) return;

        // Menghitung kecepatan jatuh berdasarkan gravitasi
        double velocityY = player.getVelocityY() + gravity;

        player.setVelocityY(velocityY);
        player.setPosY(player.getPrecisePosY() + velocityY);

        // Menggerakkan semua pipa ke arah kiri
        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        // Menambah skor ketika player berhasil melewati pipa atas
        for (Pipe pipe : pipes) {
            if (pipe.getPosY() < 0
                    && !pipe.isCounted()
                    && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {

                score++;
                pipe.setCounted(true);

                // Memainkan suara ketika skor bertambah
                scoreSound.play();
            }
        }

        // Menghapus pipa yang sudah melewati batas kiri layar
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);

            if (pipe.getPosX() + pipe.getWidth() < 0) {
                pipes.remove(i);
            }
        }

        // Memeriksa apakah player bertabrakan dengan objek lain
        checkCollision();
    }

    // Memeriksa tabrakan antara player dan pipa
    public void checkCollision() {
        // Membuat area tabrakan berdasarkan posisi dan ukuran player
        Rectangle playerRect = new Rectangle(
                player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight()
        );

        // Memeriksa tabrakan player dengan setiap pipa
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(
                    pipe.getPosX(), pipe.getPosY(),
                    pipe.getWidth(), pipe.getHeight()
            );

            // Mengakhiri permainan jika kedua area saling bersentuhan
            if (playerRect.intersects(pipeRect)) {
                endGame();
                return;
            }
        }

        // Memeriksa apakah player menyentuh batas bawah layar
        if (player.getPosY() + player.getHeight() >= 640) {
            endGame();
        }
    }

    // Mengakhiri permainan ketika terjadi tabrakan
    public void endGame() {
        if (gameOver) return;

        gameOver = true;

        // Menghentikan seluruh timer permainan
        gameLoop.stop();
        pipesCooldown.stop();

        // Menghentikan musik latar dan memainkan suara Game Over
        bgm.stop();
        gameOverSound.play();

        // Memperbarui tampilan setelah permainan berakhir
        if (view != null) {
            view.repaint();
        }
    }

    // Mengatur ulang kondisi permainan agar dapat dimainkan kembali
    public void restart() {
        // Mengembalikan posisi dan kecepatan player ke kondisi awal
        player.setPosX(100);
        player.setPosY(250);
        player.setVelocityY(0);

        // Menghapus semua pipa dan mengatur ulang skor
        pipes.clear();
        score = 0;

        // Mengembalikan status permainan ke kondisi awal
        gameStarted = false;
        gameOver = false;

        // Menghentikan timer sebelum permainan dimulai kembali
        gameLoop.stop();
        pipesCooldown.stop();

        // Menghentikan audio dari permainan sebelumnya
        gameOverSound.stop();
        bgm.stop();

        // Memperbarui tampilan setelah permainan di-reset
        if (view != null) {
            view.updateScore();
            view.repaint();
            view.requestFocusInWindow();
        }
    }

    // Dipanggil setiap kali timer gameLoop berjalan
    @Override
    public void actionPerformed(ActionEvent e) {
        // Memperbarui posisi player dan pipa
        move();

        // Memperbarui skor dan menggambar ulang tampilan
        if (view != null) {
            view.updateScore();
            view.repaint();
        }
    }

    // Mengatur tindakan berdasarkan tombol keyboard yang ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        // Memeriksa input ketika permainan sudah berakhir
        if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restart();
            }
            return;
        }

        // Menjalankan aksi ketika tombol SPACE ditekan
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;

                // Menjalankan timer pergerakan dan pembuatan pipa
                gameLoop.start();
                pipesCooldown.start();

                // Memutar musik latar secara berulang
                bgm.loop();
            }

            // Memainkan suara ketika player mengepakkan sayap
            flapSound.play();

            // Memberikan kecepatan ke atas pada player
            player.setVelocityY(-9);
        }
    }

    // Method bawaan KeyListener yang tidak digunakan
    @Override
    public void keyTyped(KeyEvent e) {}

    // Method bawaan KeyListener yang tidak digunakan
    @Override
    public void keyReleased(KeyEvent e) {}
}

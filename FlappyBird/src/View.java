
import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private Logic logic;
    private Image backgroundImg;
    private JLabel scoreLabel;

    public View(Logic logic) {
        this.logic = logic;

        // Mengatur ukuran panel dan layout
        setPreferredSize(new Dimension(360, 640));
        setFocusable(true);
        setLayout(null);

        // Menghubungkan input keyboard dengan class Logic
        addKeyListener(logic);

        // Memuat gambar background dari folder assets
        backgroundImg = new ImageIcon(
                getClass().getResource("/assets/Background.png")
        ).getImage();

        // Membuat label untuk menampilkan skor
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(100, 20, 160, 40);

        // Menambahkan label skor ke dalam panel
        add(scoreLabel);
    }

    // Memperbarui teks skor sesuai nilai dari Logic
    public void updateScore() {
        scoreLabel.setText("Score: " + logic.getScore());
    }

    // Menggambar seluruh objek permainan pada panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Menggunakan Graphics2D untuk mendukung transformasi gambar
        Graphics2D g2d = (Graphics2D) g;

        // Menggambar background memenuhi ukuran panel
        g2d.drawImage(
                backgroundImg,
                0, 0, 360, 640,
                null
        );

        // Menggambar seluruh pipa berdasarkan data dari Logic
        for (Pipe pipe : logic.getPipes()) {
            Graphics2D pipeGraphics =
                    (Graphics2D) g2d.create();

            if (pipe.getPosY() < 0) {
                // Memutar gambar 180 derajat untuk pipa bagian atas
                pipeGraphics.translate(
                        pipe.getPosX() + pipe.getWidth(),
                        pipe.getPosY() + pipe.getHeight()
                );

                pipeGraphics.rotate(Math.PI);

                // Menggambar bagian gambar pipa yang diperlukan
                pipeGraphics.drawImage(
                        pipe.getImage(),
                        0, 0,
                        pipe.getWidth(),
                        pipe.getHeight(),
                        159, 134, 275, 450,
                        null
                );
            } else {
                // Menggambar pipa bagian bawah tanpa rotasi
                pipeGraphics.drawImage(
                        pipe.getImage(),
                        pipe.getPosX(),
                        pipe.getPosY(),
                        pipe.getPosX() + pipe.getWidth(),
                        pipe.getPosY() + pipe.getHeight(),
                        159, 134, 275, 450,
                        null
                );
            }

            // Membersihkan Graphics2D setelah selesai menggambar pipa
            pipeGraphics.dispose();
        }

        // Mengambil data player dari Logic
        Player player = logic.getPlayer();

        // Menggambar player sesuai posisi dan ukurannya
        g2d.drawImage(
                player.getImage(),
                player.getPosX(),
                player.getPosY(),
                player.getWidth(),
                player.getHeight(),
                null
        );

        // Menampilkan instruksi sebelum permainan dimulai
        if (!logic.isGameStarted() && !logic.isGameOver()) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("PRESS SPACE TO START", 55, 320);
        }

        // Menampilkan layar Game Over ketika permainan berakhir
        if (logic.isGameOver()) {
            // Memberikan lapisan gelap pada layar permainan
            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(0, 0, 360, 640);

            // Menampilkan tulisan Game Over
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 32));
            g2d.drawString("GAME OVER", 75, 280);

            // Menampilkan skor akhir
            g2d.setFont(new Font("Arial", Font.PLAIN, 18));
            g2d.drawString(
                    "Score: " + logic.getScore(),
                    130, 325
            );

            // Menampilkan instruksi untuk mengulang permainan
            g2d.drawString(
                    "Tekan R untuk Restart",
                    75, 365
            );
        }
    }
}


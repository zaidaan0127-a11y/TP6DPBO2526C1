
import javax.swing.*;
import java.awt.*;

public class App {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public App() {
        // Membuat jendela utama permainan
        frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Mengatur CardLayout untuk berpindah antarhalaman
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Membuat panel menu utama
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(135, 206, 235));

        // Membuat judul permainan
        JLabel title = new JLabel("FLAPPY BIRD");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Membuat tombol untuk memulai dan keluar dari permainan
        JButton startButton = new JButton("Mulai Permainan");
        JButton exitButton = new JButton("Keluar");

        // Mengatur posisi tombol agar berada di tengah
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menyusun komponen pada menu utama
        menu.add(Box.createVerticalGlue());
        menu.add(title);
        menu.add(Box.createVerticalStrut(50));
        menu.add(startButton);
        menu.add(Box.createVerticalStrut(15));
        menu.add(exitButton);
        menu.add(Box.createVerticalGlue());

        // Mengatur aksi ketika tombol ditekan
        startButton.addActionListener(e -> startGame());
        exitButton.addActionListener(e -> System.exit(0));

        // Menambahkan menu ke panel utama dan menampilkan jendela
        mainPanel.add(menu, "MENU");
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    // Membuat dan menampilkan permainan
    private void startGame() {
        // Memuat gambar player dari folder assets
        ImageIcon birdIcon = new ImageIcon(
                getClass().getResource("/assets/Bird.png")
        );

        // Membuat objek player dengan posisi dan ukuran awal
        Player player = new Player(
                100, 250, 34, 24, birdIcon.getImage()
        );

        // Membuat objek Logic dan View untuk menjalankan permainan
        Logic logic = new Logic(player);
        View view = new View(logic);

        // Menghubungkan Logic dengan View
        logic.setView(view);

        // Menambahkan halaman permainan dan berpindah dari menu
        mainPanel.add(view, "GAME");
        cardLayout.show(mainPanel, "GAME");

        // Mengatur fokus keyboard agar input permainan dapat diterima
        SwingUtilities.invokeLater(() -> view.requestFocusInWindow());
    }

    // Method utama untuk menjalankan aplikasi
    public static void main(String[] args) {
        // Menjalankan aplikasi melalui Event Dispatch Thread Swing
        SwingUtilities.invokeLater(App::new);
    }
}

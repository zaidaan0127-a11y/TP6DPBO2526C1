
import java.awt.*;

public class Player {
    // Menyimpan posisi player pada layar
    private int posX;
    private double posY;

    // Menyimpan ukuran player
    private int width;
    private int height;

    private Image image;

    // Menyimpan kecepatan gerak vertikal player
    private double velocityY;

    // Menginisialisasi posisi, ukuran, gambar, dan kecepatan awal player
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = 0;
    }

    // Getter dan setter untuk posisi horizontal
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }

    // Getter dan setter untuk posisi vertikal
    public int getPosY() { return (int) posY; }
    public double getPrecisePosY() { return posY; }
    public void setPosY(double posY) { this.posY = posY; }

    // Getter dan setter untuk lebar player
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    // Getter dan setter untuk tinggi player
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    // Getter dan setter untuk gambar player
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    // Getter dan setter untuk kecepatan vertikal player
    public double getVelocityY() { return velocityY; }
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }
}




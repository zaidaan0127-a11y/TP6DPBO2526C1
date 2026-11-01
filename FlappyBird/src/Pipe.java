
import java.awt.*;

public class Pipe {
    // Menyimpan posisi dan ukuran pipa
    private int posX, posY, width, height;

    // Menyimpan gambar pipa
    private Image image;

    // Menandai apakah pipa sudah dihitung untuk penambahan skor
    private boolean counted;

    // Menginisialisasi posisi, ukuran, dan gambar pipa
    public Pipe(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.counted = false;
    }

    // Getter dan setter untuk posisi horizontal
    public int getPosX() { return posX; }
    public void setPosX(int posX) { this.posX = posX; }

    // Getter dan setter untuk posisi vertikal
    public int getPosY() { return posY; }
    public void setPosY(int posY) { this.posY = posY; }

    // Getter dan setter untuk lebar pipa
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    // Getter dan setter untuk tinggi pipa
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    // Getter dan setter untuk gambar pipa
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    // Memeriksa dan mengubah status perhitungan skor pipa
    public boolean isCounted() { return counted; }
    public void setCounted(boolean counted) {
        this.counted = counted;
    }
}

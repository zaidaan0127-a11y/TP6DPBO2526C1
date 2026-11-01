# TP6DPBO2526C1

Janji

Saya Zaidaan Dhyaa Ulhaq Budiono dengan NIM 2500945 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# FLAPPY BIRD

## 1. Desain Program

Program Flappy Bird dibuat menggunakan bahasa pemrograman Java dengan library Swing dan AWT untuk menampilkan antarmuka grafis serta menjalankan permainan.

Program menggunakan beberapa class yang memiliki tanggung jawab masing-masing agar kode lebih terstruktur dan mudah dikelola.

### Struktur Class

| Class | Fungsi |
|---|---|
| `App` | Mengatur jendela utama, Main Menu, serta perpindahan ke halaman permainan. |
| `View` | Menampilkan background, player, pipa, skor, dan tampilan Game Over. |
| `Logic` | Mengatur mekanisme permainan, pergerakan, gravitasi, skor, tabrakan, restart, dan audio. |
| `Player` | Menyimpan data player seperti posisi, ukuran, gambar, dan kecepatan. |
| `Pipe` | Menyimpan data pipa seperti posisi, ukuran, gambar, dan status perhitungan skor. |
| `AudioPlayer` | Mengatur pemutaran audio seperti BGM dan sound effect. |

### Hubungan Antar-Class

- `App` membuat dan menghubungkan objek `Player`, `Logic`, dan `View`.
- `Logic` mengatur jalannya permainan dan menggunakan objek `Player` serta `Pipe`.
- `View` mengambil data dari `Logic` untuk menggambar objek permainan.
- `AudioPlayer` digunakan oleh `Logic` untuk memutar dan menghentikan audio.

## 2. Penjelasan Alur Program

Program dimulai dengan menampilkan Main Menu yang memiliki dua tombol, yaitu **Mulai Permainan** dan **Keluar**.

### Alur Permainan

1. Ketika program dijalankan, `App` membuat jendela utama dan menampilkan Main Menu.
2. Pemain menekan tombol **Mulai Permainan** untuk masuk ke halaman permainan.
3. Permainan menampilkan player dan instruksi untuk menekan tombol SPACE.
4. Ketika tombol SPACE ditekan, permainan dimulai, BGM diputar, dan player bergerak ke atas.
5. Gravitasi membuat player bergerak turun kembali jika tidak menekan SPACE.
6. Pipa muncul secara berkala dari sisi kanan dan bergerak ke kiri.
7. Setiap kali player berhasil melewati pipa, skor bertambah satu dan sound effect dimainkan.
8. Jika player menabrak pipa atau jatuh ke batas bawah layar, permainan berakhir dan menampilkan Game Over.
9. Pemain dapat menekan tombol **R** untuk melakukan restart.
10. Setelah restart, posisi player, pipa, dan skor dikembalikan ke kondisi awal. Pemain dapat memulai permainan kembali dengan SPACE.

### Kontrol Permainan

| Tombol | Fungsi |
|---|---|
| SPACE | Memulai permainan dan membuat player mengepak ke atas. |
| R | Mengulang permainan setelah Game Over. |

## 3. Dokumentasi Saat Program Dijalankan

Dokumentasi berikut berisi tampilan program pada setiap tahap penggunaan. Screenshot diambil ketika program dijalankan.

### 3.1 Main Menu

Menampilkan halaman awal permainan dengan judul Flappy Bird serta dua tombol, yaitu Mulai Permainan dan Keluar.

<img width="261" height="476" alt="Screenshot 2026-11-01 113846" src="https://github.com/user-attachments/assets/3bc2764a-0f84-46ab-a619-d1860fb2973a" />

### 3.2 Tampilan Permainan
Setelah menekan tombol Mulai Permainan, halaman permainan ditampilkan. Player berada pada posisi awal dan terdapat instruksi untuk menekan tombol SPACE.
Jalannya permainan saat dimulai dan berakhirnya permainan.

https://github.com/user-attachments/assets/de2a36d2-b32d-47cc-bcfa-b5c323716c12



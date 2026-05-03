/**
 * Tugas Kelompok 3 (Week 8 - Rekursif)
 * Deskripsi: Implementasi sistem playlist menggunakan metode rekursif.
 * Bagian 1: totalDurasi() 
 * Nama: Renata Ramadhanyandra
 * Bagian 2: tampilkanMundur()
 * Nama: Dustin Ballqis Saputra
 * Bagian 3: CariDurasiTerpanjang()
 * Nama: Daffa Fathur Rahman
 */

class Lagu {
    private String judul;
    private String penyanyi;
    private double durasi; // dalam menit

    public Lagu(String judul, String penyanyi, double durasi) {
        this.judul    = judul;
        this.penyanyi = penyanyi;
        this.durasi   = durasi;
    }

    public String getJudul()    { return judul; }
    public String getPenyanyi() { return penyanyi; }
    public double getDurasi()   { return durasi; }
}

public class PlaylistRekursif {
    
     /**
     * Tujuan        : Menghitung total durasi seluruh lagu dalam playlist secara rekursif.
     *
     * Base case     : n == 0
     *                 Tidak ada lagu yang perlu dijumlahkan → kembalikan 0.0
     *
     * Recursive case: Ambil durasi lagu ke-(n-1), lalu tambahkan dengan hasil
     *                 pemanggilan rekursif totalDurasi(list, n-1).
     *                 → list[n-1].getDurasi() + totalDurasi(list, n-1)
     *
     * Kompleksitas  : O(n)
     *                 Fungsi dipanggil sebanyak n+1 kali (n lagu + 1 base case).
     *                 Setiap pemanggilan melakukan 1 operasi penjumlahan → linear.
     */
    public static double totalDurasi(Lagu[] list, int n) {
        if (n == 0) return 0.0;
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /**
     * Tujuan        : Menampilkan daftar lagu dari belakang (rekursif)
     *
     * Base case     : n == 0 → berhenti
     *
     * Recursive case: cetak lagu ke-(n-1), lalu panggil ke sisa data
     *
     * Kompleksitas  : O(n)
     */
    public static void tampilkanMundur(Lagu[] list, int n) {
        if (n == 0) return;
        Lagu lagu = list[n - 1];
        System.out.println(lagu.getJudul() + " - " + lagu.getPenyanyi() + " (" + lagu.getDurasi() + " menit)");
        tampilkanMundur(list, n - 1);
    }

    /**
     * Bagian 3: cariDurasiTerpanjang() (Rekursif)
     * Tujuan        : Mencari durasi lagu terbesar dalam playlist secara rekursif.
     * 
     * Base Case     : n == 1 
     *                 Jika hanya tersisa satu lagu, maka lagu tersebut memiliki durasi terpanjang.[cite: 1]
     * 
     * Recursive Case: Membandingkan lagu pada indeks terakhir dengan nilai maksimum 
     *                 dari sisa lagu yang diproses oleh pemanggilan rekursif.[cite: 1]
     * 
     * Analisis Kritis: Penggunaan variabel bantuan 'maxSisa' sangat penting untuk 
     *                 mencegah pemanggilan fungsi berulang yang tidak efisien (redundant).
     * 
     * Kompleksitas  : O(n) karena setiap elemen diperiksa tepat satu kali.[cite: 2]
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int n) {
        // Base case: jika tinggal satu lagu
        if (n == 1) {
            return list[0].getDurasi();
        }

        // Recursive case
        double maxSisa = cariDurasiTerpanjang(list, n - 1);
        
        // Membandingkan elemen saat ini dengan pemenang dari sisa playlist
        if (list[n - 1].getDurasi() > maxSisa) {
            return list[n - 1].getDurasi();
        } else {
            return maxSisa;
        }
    }

    public static void main(String[] args) {
        // Data dummy: 5 lagu
        Lagu[] playlist = {
            new Lagu("Perfect",      "Ed Sheeran", 4.23),
            new Lagu("Shivers",      "Ed Sheeran", 3.50),
            new Lagu("Fix You",      "Coldplay",   4.52),
            new Lagu("Yellow",       "Coldplay",   4.29),
            new Lagu("Viva La Vida", "Coldplay",   4.03)
        };

        // --- EKSEKUSI BAGIAN 1 ---
        System.out.println("=== BAGIAN 1: totalDurasi() ===");
        long mulai1 = System.nanoTime();
        double total = totalDurasi(playlist, playlist.length);
        long selesai1 = System.nanoTime();
        System.out.printf("Total durasi         : %.2f menit%n", total);
        System.out.println("Execution Time       : " + (selesai1 - mulai1) / 1_000_000 + " ms");

        // --- EKSEKUSI BAGIAN 2 ---
        System.out.println("\n=== BAGIAN 2: tampilkanMundur() ===");
        long mulai2 = System.nanoTime();
        tampilkanMundur(playlist, playlist.length);
        long selesai2 = System.nanoTime();
        System.out.println("Execution Time       : " + (selesai2 - mulai2) / 1_000_000 + " ms");

        // --- EKSEKUSI BAGIAN 3 ---
        System.out.println("\n=== BAGIAN 3: cariDurasiTerpanjang() ===");
        long mulai3 = System.nanoTime();
        double max = cariDurasiTerpanjang(playlist, playlist.length);
        long selesai3 = System.nanoTime();
        System.out.printf("Durasi terpanjang    : %.2f menit%n", max);
        System.out.println("Execution Time       : " + (selesai3 - mulai3) / 1_000_000 + " ms");
        System.out.println("\n--- Analisis Kompleksitas Bagian 3 ---");
        System.out.println("Growth rate          : Linear");
        System.out.println("Kompleksitas waktu   : O(n)");
    }
}

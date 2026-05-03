/**
     * Bagian 3: cariDurasiTerpanjang()
     * Nama : Daffa Fathur Rahman
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int n) {
        if (n == 1) {
            return list[0].getDurasi();
        }

        // Recursive case
        // Kita simpan hasil rekursi ke variabel 
        double maxSisa = cariDurasiTerpanjang(list, n - 1);
        
        // Bandingkan lagu terakhir
        if (list[n - 1].getDurasi() > maxSisa) {
            return list[n - 1].getDurasi();
        } else {
            return maxSisa;
        }
    }

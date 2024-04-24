import java.util.Scanner;

public class Kasir {
    private Meja[] daftarMeja;
    private Menu[] daftarMenu;

    public Kasir() {
        daftarMeja = new Meja[10];
        for (int i = 0; i < 10; i++) {
            daftarMeja[i] = new Meja(i + 1);
        }

        daftarMenu = new Menu[5];
        daftarMenu[0] = new Menu("Nasi Goreng", 15000);
        daftarMenu[1] = new Menu("Mi Goreng", 15000);
        daftarMenu[2] = new Menu("Capcay", 20000);
        daftarMenu[3] = new Menu("Bihun Goreng", 17000);
        daftarMenu[4] = new Menu("Ayam Koloke", 25000);
    }

    // digunakan untuk menampilkan daftar meja beserta keterangan ketersediaannya
    // gunakan method isKosong pada class Kasir agar lebih mudah
    public void tampilkanDaftarMeja() {
        System.out.println("Daftar Meja:");
        for (int i = 0; i < daftarMeja.length; i++) {
            Meja meja = daftarMeja[i];
            if (meja.isKosong()) {
                System.out.println("Meja " + meja.getNomorMeja() + ": Kosong");
            } else {
                Pelanggan pelanggan = meja.getPelanggan();
                System.out.println("Meja " + meja.getNomorMeja() + ": Terisi oleh pelanggan " + pelanggan.getNama());
            }
        }
    }

    // untuk menambahkan pelanggan pada meja tertentu
    // jika meja kosong tambahkan pelanggan pada meja tersebut
    // jika tidak buatlah keterangan bahwa meja sudah ada pelanggan
    public void tambahPelanggan(int nomorMeja, Pelanggan pelanggan) {
        //EDIT DISINI
        Meja meja = daftarMeja[nomorMeja - 1];
    if (meja.isKosong()) {
        meja.setPelanggan(pelanggan);
        System.out.println("Pelanggan " + pelanggan.getNama() + " ditambahkan pada Meja " + nomorMeja);
    } else {
        System.out.println("Maaf, Meja " + nomorMeja + " sudah ada pelanggan.");
    }
    }
    
    // menambah pesanan menu pada nomor meja
    // jika menu tidak ada dalam daftar maka tampilkan "Menu is null" 
    public void tambahPesanan(int nomorMeja, Menu menu) {
        //EDIT DISINI
        Meja meja = daftarMeja[nomorMeja - 1];
    if (!meja.isKosong()) {
        meja.setMenu(menu);
        System.out.println("Pesanan " + menu.getNama() + " ditambahkan pada Meja " + nomorMeja);
    } else {
        System.out.println("Maaf, Meja " + nomorMeja + " belum ada pelanggan.");
    }
    }

    // Menghapus pelanggan 
    public void hapusPelanggan(int nomorMeja) {
        //EDIT DISINI
        Meja meja = daftarMeja[nomorMeja - 1];
    if (!meja.isKosong()) {
        meja.setPelanggan(null);
        System.out.println("Pelanggan pada Meja " + nomorMeja + " berhasil dihapus.");
    } else {
        System.out.println("Maaf, Meja " + nomorMeja + " tidak ada pelanggan.");
    }
}

    public int hitungHargaPesanan(int nomorMeja) {
        int totalHarga = 0;
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    totalHarga += menu[i].getHarga();
                }
            }
            return totalHarga;
        }
        return totalHarga;
    }

    public void tampilkanPesanan(int nomorMeja) {
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    System.out.println("Meja " + nomorMeja + " - " + pelanggan.getNama() + " memesan " + menu[i].getNama() + " seharga " + menu[i].getHarga());
                }
            }
        } else {
            System.out.println("Meja " + nomorMeja + " tidak memiliki pesanan");
        }
    }    

    public void tampilkanDaftarMenu() {
        System.out.println("Daftar Menu:");
        System.out.println("1. Nasi Goreng - Rp15.000");
        System.out.println("2. Mi Goreng - Rp15.000");
        System.out.println("3. Capcay - Rp20.000");
        System.out.println("4. Bihun Goreng - Rp17.000");
        System.out.println("5. Ayam Koloke - Rp25.000");
        System.out.println("6. Simpan");
        System.out.println();
    }

    public void tampilkanDaftarFitur() {
        System.out.println("1. Tampilkan daftar meja");
        System.out.println("2. Tambah pelanggan");
        System.out.println("3. Tambah pesanan");
        System.out.println("4. Hapus pelanggan");
        System.out.println("5. Hitung harga pesanan");
        System.out.println("6. Tampilkan pesanan di meja");
        System.out.println("0. Keluar");
    }

    public void jalankan() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanDaftarFitur();
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();  
            switch (pilihan) {
                case 1:
                    tampilkanDaftarMeja();
                    break;
                case 2:
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaTambahPelanggan = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan nama pelanggan: ");
                    String namaPelanggan = scanner.nextLine();
                    Pelanggan pelanggan = new Pelanggan(namaPelanggan);
                    tambahPelanggan(nomorMejaTambahPelanggan, pelanggan);
                    break;
                case 3:
                    boolean stopLoop = false;
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaPesan = scanner.nextInt();
                    Boolean meja = daftarMeja[nomorMejaPesan - 1].isKosong();
                    scanner.nextLine();  
                    if (!meja) {
                        tampilkanDaftarMenu();
                        while (!stopLoop) {
                            System.out.print("Masukkan nomor menu: ");
                            int nomorMenuPesan = scanner.nextInt();
                            scanner.nextLine();  
                            switch (nomorMenuPesan) {
                                case 1:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[0]);
                                    break;
                                case 2:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[1]);
                                    break;
                                case 3:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[2]);
                                    break;
                                case 4:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[3]);
                                    break;
                                case 5:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[4]);
                                    break;
                                case 6:
                                    stopLoop = true;
                                    break;
                                default:
                                    System.out.println("Nomor menu tidak valid");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Meja tidak ada pelanggan");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan nomor meja yang akan dihapus pelanggannya: ");
                    int nomorMejaHapusPelanggan = scanner.nextInt();
                    hapusPelanggan(nomorMejaHapusPelanggan);
                    break;
                case 5:
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaHitungHarga = scanner.nextInt();
                    int totalHarga = hitungHargaPesanan(nomorMejaHitungHarga);
                    if (totalHarga > 0) {
                        System.out.println("Total harga pesanan di Meja " + nomorMejaHitungHarga + " adalah: Rp" + totalHarga);
                    } else {
                        System.out.println("Belum ada pesanan di Meja " + nomorMejaHitungHarga + ".");
                    }
                    break;
                case 6:
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaTampilkanPesanan = scanner.nextInt();
                    scanner.nextLine();
                    tampilkanPesanan(nomorMejaTampilkanPesanan);
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi kasir restoran!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
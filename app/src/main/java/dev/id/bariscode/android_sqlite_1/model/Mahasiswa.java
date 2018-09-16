package dev.id.bariscode.android_sqlite_1.model;

public class Mahasiswa {

    //Todo 1: Buat Model Mahasiswa
    private String id, nim, nama, alamat;

    //Todo 2: Buat Inteface Model
    public Mahasiswa(String id, String nim, String nama, String alamat) {
        this.id  = id;
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
    }

    public Mahasiswa() {

    }

    //Todo 3: Buat Setter & Getter [Tekan Alt + Insert -> Pilih Setter & Getter ]

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

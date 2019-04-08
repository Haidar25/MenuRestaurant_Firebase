package com.example.HAIDAR_1202164150_SI4006_PAB_MODUL4;

public class Card {
    String lokasi_image,nama,harga,desc;

    public Card(String lokasi_image, String nama, String harga, String deskripsi) {
        this.lokasi_image = lokasi_image;
        this.nama = nama;
        this.harga = harga;
        this.desc = deskripsi;
    }

    public String getLokasi_image() {
        return lokasi_image;
    }

    public void setLokasi_image(String lokasi_image) {
        this.lokasi_image = lokasi_image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return desc;
    }

    public void setDeskripsi(String deskripsi) {
        this.desc = deskripsi;
    }
}

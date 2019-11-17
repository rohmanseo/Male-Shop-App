package com.komsi.maleshop;

public class Produk {
    private String idProduk;
    private String merk;
    private int thumbnail;
    private String harga;
    private String Deskripsi;
    private Double Rating;
    private String Colour;


    public Produk(String idProduk, String merk, int thumbnail, String harga, String deskripsi, Double rating, String colour) {
        this.idProduk = idProduk;
        this.merk = merk;
        this.thumbnail = thumbnail;
        this.harga = harga;
        Deskripsi = deskripsi;
        Rating = rating;
        Colour = colour;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String colour) {
        Colour = colour;
    }
}








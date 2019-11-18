package com.komsi.maleshop.model;

public class Produk {
    private String idProduk;
    private String nama;
    private int thumbnail;
    private String harga;
    private String Deskripsi;
    private Double Rating;
    private String Colour;
    private String Size;


    public Produk(String idProduk, String nama, int thumbnail, String harga, String deskripsi, Double rating, String colour, String size) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.thumbnail = thumbnail;
        this.harga = harga;
        Deskripsi = deskripsi;
        Rating = rating;
        Colour = colour;
        Size = size;
    }

    public String getIdProduk() {

        return idProduk;
    }

    public void setIdProduk(String idProduk) {

        this.idProduk = idProduk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}








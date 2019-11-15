package com.komsi.maleshop;

public class RvNewArrival {
    private String merk;
    private int thumbnail;
    private String harga;

    public RvNewArrival(String merk, int thumbnail, String harga) {
        this.merk = merk;
        this.thumbnail = thumbnail;
        this.harga = harga;
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
}








package com.komsi.maleshop;

public class Slide {
    private int image;
    private String Title;

    public Slide(int image, String title) {
        this.image = image;
        Title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

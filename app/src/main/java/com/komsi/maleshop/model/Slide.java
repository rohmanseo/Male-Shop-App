package com.komsi.maleshop.model;

public class Slide {
    private String image;
    private String Title;

    public Slide(String image, String title) {
        this.image = image;
        Title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

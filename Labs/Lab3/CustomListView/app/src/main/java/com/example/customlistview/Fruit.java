package com.example.customlistview;
import android.net.Uri;
public class Fruit {
    private String name;
    private String description;
    private Uri image;

    public Fruit() {
    }

    public Fruit(String name, String description, Uri image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}

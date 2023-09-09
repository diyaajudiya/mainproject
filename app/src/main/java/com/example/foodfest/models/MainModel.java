package com.example.foodfest.models;

import com.example.foodfest.MainActivity;
//not
public class MainModel extends MainActivity {
    int image;
    String name , Price , Description;

    public MainModel(int image, String name, String price, String description) {
        this.image = image;
        this.name = name;
        Price = price;
        Description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

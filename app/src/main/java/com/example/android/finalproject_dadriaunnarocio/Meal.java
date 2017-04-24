package com.example.android.finalproject_dadriaunnarocio;

import java.io.Serializable;

/**
 * Created by ccteuser on 4/9/17.
 */

public class Meal implements Serializable {

    public String description;
    public double price;
    public int calories;
    public boolean isVegetarian;
    public int photoId;

    public Meal(String description, double price, int calories, boolean isVegetarian, int photoId) {
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
        this.photoId = photoId;
    }

    public Meal() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}

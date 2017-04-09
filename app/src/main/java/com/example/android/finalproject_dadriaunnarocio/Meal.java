package com.example.android.finalproject_dadriaunnarocio;

/**
 * Created by ccteuser on 4/9/17.
 */

public class Meal {

    private String description;
    private double price;
    private int calories;
    private boolean isVegetarian;
    private int photoId;

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

package com.example.android.finalproject_dadriaunnarocio;

/**
 * Created by ccteuser on 4/11/17.
 */

public class Student {
    public String fullName;
    public int grade;
    public int age;
    public String favFood;
    public String school;
    public int photoId;
    public boolean isVegetarian;

    public Student(String fullName, int grade, int age, String favFood, String school, int photoId, boolean isVegetarian) {
        this.fullName = fullName;
        this.grade = grade;
        this.age = age;
        this.favFood = favFood;
        this.school = school;
        this.photoId = photoId;
        this.isVegetarian = isVegetarian;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavFood() {
        return favFood;
    }

    public void setFavFood(String favFood) {
        this.favFood = favFood;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }
}

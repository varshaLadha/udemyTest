package com.example.mac2018_10_01.udemyapp.modalClasses;

public class CategoryData {

    int id;
    String courseName, imageUrl;

    public CategoryData() {}

    public CategoryData(int id, String courseName, String imageUrl) {
        this.id = id;
        this.courseName = courseName;
        this.imageUrl = imageUrl;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

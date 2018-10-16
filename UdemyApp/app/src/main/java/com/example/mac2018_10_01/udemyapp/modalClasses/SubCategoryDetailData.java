package com.example.mac2018_10_01.udemyapp.modalClasses;

public class SubCategoryDetailData {

    int id, subCategoryId, rating, peopleRated, cost;
    String title, tag, instructorName, imageUrl;
    float averageRating;

    public SubCategoryDetailData() {}

    public SubCategoryDetailData(int id, int subCategoryId, int rating, int peopleRated, float averageRating, int cost, String title, String tag, String instructorName, String imageUrl) {
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.rating = rating;
        this.peopleRated = peopleRated;
        this.averageRating = averageRating;
        this.cost = cost;
        this.title = title;
        this.tag = tag;
        this.instructorName = instructorName;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPeopleRated() {
        return peopleRated;
    }

    public void setPeopleRated(int peopleRated) {
        this.peopleRated = peopleRated;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

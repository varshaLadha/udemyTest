package com.example.mac2018_10_01.udemyapp.modalClasses;

public class SubCategoryData {

    int id, categoryId;
    String subCategoryName;

    public SubCategoryData(){}

    public SubCategoryData(int id, int categoryId, String subCategoryName) {
        this.id = id;
        this.categoryId = categoryId;
        this.subCategoryName = subCategoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}

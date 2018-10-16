package com.example.mac2018_10_01.udemyapp.modalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoryDetailResponseModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;
    @SerializedName("error")
    @Expose
    private String error;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public class Response {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("subCategoryId")
        @Expose
        private Integer subCategoryId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("tag")
        @Expose
        private String tag;
        @SerializedName("instructorName")
        @Expose
        private String instructorName;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("peopleRated")
        @Expose
        private Integer peopleRated;
        @SerializedName("averageRating")
        @Expose
        private Float averageRating;
        @SerializedName("cost")
        @Expose
        private Integer cost;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSubCategoryId() {
            return subCategoryId;
        }

        public void setSubCategoryId(Integer subCategoryId) {
            this.subCategoryId = subCategoryId;
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

        public void setTag(String  tag) {
            this.tag = tag;
        }

        public String getInstructorName() {
            return instructorName;
        }

        public void setInstructorName(String instructorName) {
            this.instructorName = instructorName;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Integer getPeopleRated() {
            return peopleRated;
        }

        public void setPeopleRated(Integer peopleRated) {
            this.peopleRated = peopleRated;
        }

        public Float getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(Float averageRating) {
            this.averageRating = averageRating;
        }

        public Integer getCost() {
            return cost;
        }

        public void setCost(Integer cost) {
            this.cost = cost;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}

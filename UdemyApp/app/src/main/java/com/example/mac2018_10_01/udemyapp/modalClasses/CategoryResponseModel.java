package com.example.mac2018_10_01.udemyapp.modalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponseModel {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("response")
    @Expose
    private List<Response> response = null;

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

    public class Response {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("error")
        @Expose
        private String error;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

    }
}

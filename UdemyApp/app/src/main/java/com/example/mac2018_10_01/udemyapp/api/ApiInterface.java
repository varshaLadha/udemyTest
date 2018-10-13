package com.example.mac2018_10_01.udemyapp.api;

import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryResponseModel;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailResponseModel;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryResponseModel;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponseModel> userLoginEmail(@Field("email") String email, @Field("password") String password, @Field("loginType") String loginType);

    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponseModel> userLoginGoogle(@Field("userName") String userName, @Field("email") String email, @Field("loginType") String loginType, @Field("emailTerms") Boolean emailTerms);

    @FormUrlEncoded
    @POST("user/signUp")
    Call<UserResponseModel> userSignUp(@Field("userName") String userName, @Field("email") String email, @Field("password") String password, @Field("loginType") String loginType, @Field("emailTerms") Boolean emailTerms);

    @GET("category/")
    Call<CategoryResponseModel> getCategoryData();

    @GET("subcategory/category/{categoryId}")
    Call<SubCategoryResponseModel> getSubCategoryData(@Path("categoryId") int categoryId);

    @GET("course/subcategory/{subCategoryId}")
    Call<SubCategoryDetailResponseModel> getSubCategoryDetail(@Path("subCategoryId") int subCategoryId);

}

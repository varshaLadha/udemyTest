package com.example.mac2018_10_01.udemyapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.activity.SigninActivity;
import com.example.mac2018_10_01.udemyapp.adapters.CategoryDataAdapter;
import com.example.mac2018_10_01.udemyapp.adapters.PopularCourseAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryResponseModel;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailData;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedFragment extends Fragment {

    RecyclerView categories, popularCourses;
    CategoryDataAdapter adapter;
    PopularCourseAdapter popularCourseAdapter;
    ArrayList<CategoryData> categoryData;
    ArrayList<SubCategoryDetailData> subCategoryDetailData;
    TextView tvSignIn;

    public FeaturedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_featured, container, false);

        initViews(view);
        getData();

        return view;
    }

    public void initViews(View view){
        categories = view.findViewById(R.id.rvCategories);
        popularCourses = view.findViewById(R.id.rvAdvanceCourse);
        categoryData = new ArrayList<CategoryData>();
        subCategoryDetailData = new ArrayList<SubCategoryDetailData>();
        tvSignIn = view.findViewById(R.id.tvSignIn);

        if(!ApplicationClass.pref.contains("userDetail")){
            tvSignIn.setVisibility(View.VISIBLE);
        }

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    public void getData(){

        categoryData.clear();
        subCategoryDetailData.clear();

        final Call<SubCategoryDetailResponseModel> popularCourse = ApplicationClass.apiInterface.getAllPopularCourses();
        popularCourse.enqueue(new Callback<SubCategoryDetailResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryDetailResponseModel> call, Response<SubCategoryDetailResponseModel> response) {
                if(response.body().getSuccess() == 1) {
                    List<SubCategoryDetailResponseModel.Response> data = response.body().getResponse();
                    for (int i = 0; i < data.size(); i++) {
                        subCategoryDetailData.add(new SubCategoryDetailData(data.get(i).getId(), data.get(i).getSubCategoryId(), data.get(i).getRating(), data.get(i).getPeopleRated(), data.get(i).getAverageRating(), data.get(i).getCost(), data.get(i).getTitle(), data.get(i).getTag(), data.get(i).getInstructorName(), data.get(i).getImageUrl()));
                    }

                    popularCourseAdapter = new PopularCourseAdapter(getContext(), subCategoryDetailData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                    popularCourses.setLayoutManager(layoutManager);
                    popularCourses.setAdapter(popularCourseAdapter);
                }else {
                    Toast.makeText(getContext(), response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubCategoryDetailResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ", t.getMessage());
            }
        });

        Call<CategoryResponseModel> call = ApplicationClass.apiInterface.getCategoryData();
        call.enqueue(new Callback<CategoryResponseModel>() {
            @Override
            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {

                if(response.body().getSuccess() == 1) {
                    List<CategoryResponseModel.Response> data = response.body().getResponse();

                    for (int i = 0; i < data.size() ; i++) {
                        categoryData.add(new CategoryData(data.get(i).getId(), data.get(i).getCategoryName(), data.get(i).getImageUrl()));
                    }

                    adapter = new CategoryDataAdapter(getContext(), categoryData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
                    categories.setLayoutManager(layoutManager);
                    categories.setAdapter(adapter);
                }else {
                    Toast.makeText(getContext(), response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ", t.getMessage());
            }
        });

    }

}

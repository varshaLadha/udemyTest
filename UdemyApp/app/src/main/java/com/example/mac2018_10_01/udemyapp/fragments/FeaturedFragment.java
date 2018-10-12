package com.example.mac2018_10_01.udemyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.adapters.CategoryDataAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedFragment extends Fragment {

    RecyclerView categories;
    CategoryDataAdapter adapter;
    ArrayList<CategoryData> categoryData;

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
        categoryData = new ArrayList<CategoryData>();
    }

    public void getData(){

        categoryData.clear();

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
                    Toast.makeText(getContext(), response.body().getResponse().get(0).getError(), Toast.LENGTH_SHORT).show();
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

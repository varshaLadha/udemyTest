package com.example.mac2018_10_01.udemyapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.adapters.CategoryDataAdapter;
import com.example.mac2018_10_01.udemyapp.adapters.SubCategoryDetailListAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryResponseModel;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailData;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedDataFragment extends Fragment {

    ImageView ivBack;
    EditText tvSearch;
    TextView tvErrMsg, tvInfo;
    String title;
    int id;
    SubCategoryDetailListAdapter adapter;
    CategoryDataAdapter adapter1;
    ArrayList<SubCategoryDetailData> subCategoryDetailData;
    ArrayList<CategoryData> categoryData;
    RecyclerView rvSearchedData;
    Boolean isSearch;

    public SearchedDataFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searched_data, container, false);

        initViews(view);
        getData();

        return view;
    }

    public void initViews(View view){
        ivBack = view.findViewById(R.id.ivBack);
        tvSearch = view.findViewById(R.id.tvSearch);
        rvSearchedData = view.findViewById(R.id.rvSearchedData);
        tvErrMsg = view.findViewById(R.id.tvErrMsg);
        tvInfo = view.findViewById(R.id.tvInfo);

        title = getArguments().getString("searchKey");
        id = getArguments().getInt("id");
        isSearch = getArguments().getBoolean("isSearch");

        subCategoryDetailData = new ArrayList<SubCategoryDetailData>();
        categoryData = new ArrayList<CategoryData>();

        tvSearch.setText(title);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    public void getData(){

        subCategoryDetailData.clear();
        categoryData.clear();

        Call<SubCategoryDetailResponseModel> call;

        if(!isSearch){
            call = ApplicationClass.apiInterface.getAllCourses(id);
        }else {
            call = ApplicationClass.apiInterface.getSearchedCourses(title);
        }


        call.enqueue(new Callback<SubCategoryDetailResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryDetailResponseModel> call, Response<SubCategoryDetailResponseModel> response) {

                if(response.body().getSuccess() == 1){
                    List<SubCategoryDetailResponseModel.Response> data = response.body().getResponse();

                    for(int i=0; i<data.size(); i++){
                        subCategoryDetailData.add(new SubCategoryDetailData(data.get(i).getId(),data.get(i).getSubCategoryId(),data.get(i).getRating(),data.get(i).getPeopleRated(), data.get(i).getAverageRating(),data.get(i).getCost(),data.get(i).getTitle(),data.get(i).getTag(),data.get(i).getInstructorName(),data.get(i).getImageUrl()));
                    }

                    adapter = new SubCategoryDetailListAdapter(getContext(), subCategoryDetailData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvSearchedData.setLayoutManager(layoutManager);
                    rvSearchedData.setAdapter(adapter);

                }else {
                    //Toast.makeText(getContext(), response.body().getError(), Toast.LENGTH_SHORT).show();
                    tvErrMsg.setText("No result found for your search "+title);
                    tvErrMsg.setVisibility(View.VISIBLE);

                    Call<CategoryResponseModel> callCategory = ApplicationClass.apiInterface.getCategoryData();
                    callCategory.enqueue(new Callback<CategoryResponseModel>() {
                        @Override
                        public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {

                            if(response.body().getSuccess() == 1) {
                                List<CategoryResponseModel.Response> data = response.body().getResponse();

                                for (int i = 0; i < data.size() ; i++) {
                                    categoryData.add(new CategoryData(data.get(i).getId(), data.get(i).getCategoryName(), data.get(i).getImageUrl()));
                                }

                                adapter1 = new CategoryDataAdapter(getContext(), categoryData);
                                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
                                rvSearchedData.setLayoutManager(layoutManager);
                                rvSearchedData.setAdapter(adapter1);
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

            @Override
            public void onFailure(Call<SubCategoryDetailResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ", t.getMessage());
            }
        });
    }
}

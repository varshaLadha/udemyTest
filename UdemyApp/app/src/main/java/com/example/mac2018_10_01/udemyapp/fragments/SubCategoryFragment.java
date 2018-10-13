package com.example.mac2018_10_01.udemyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.adapters.SubCategoryListAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryData;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryFragment extends Fragment {

    String subCategoryName;
    int id;
    ImageView ivBack;
    TextView tvTitle;
    RecyclerView rvPopularCourses, rvSubCategories;
    SubCategoryListAdapter subCategoryAdapter;
    ArrayList<SubCategoryData> subCategoryData;

    public SubCategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        initViews(view);
        getData();
        
        return view;
    }

    public void initViews(View view){

        subCategoryName = getArguments().getString("subCategory");
        id = getArguments().getInt("id");
        ivBack = view.findViewById(R.id.ivBack);
        tvTitle = view.findViewById(R.id.tvTitle);
        rvPopularCourses = view.findViewById(R.id.rvPopularCourses);
        rvSubCategories = view.findViewById(R.id.rvSubCategories);

        subCategoryData = new ArrayList<SubCategoryData>();

        tvTitle.setText(subCategoryName);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    public void getData() {

        subCategoryData.clear();

        Call<SubCategoryResponseModel> call = ApplicationClass.apiInterface.getSubCategoryData(id);
        call.enqueue(new Callback<SubCategoryResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryResponseModel> call, Response<SubCategoryResponseModel> response) {

                if(response.body().getSuccess() == 1 ){
                    List<SubCategoryResponseModel.Response> data = response.body().getResponse();

                    for(int i = 0; i<data.size(); i++){
                        subCategoryData.add(new SubCategoryData(data.get(i).getId(), data.get(i).getCategoryId(), data.get(i).getSubCategoryName()));
                    }

                    subCategoryAdapter = new SubCategoryListAdapter(getContext(), subCategoryData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvSubCategories.setLayoutManager(layoutManager);
                    rvSubCategories.setAdapter(subCategoryAdapter);
                } else {
                    Toast.makeText(getContext(), response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ", t.getMessage());
            }
        });
    }
}

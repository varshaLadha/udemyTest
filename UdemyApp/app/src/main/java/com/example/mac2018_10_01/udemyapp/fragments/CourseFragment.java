package com.example.mac2018_10_01.udemyapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import com.example.mac2018_10_01.udemyapp.adapters.SubCategoryDetailListAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailData;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseFragment extends Fragment {

    String name;
    int id;
    ImageView ivBack;
    TextView tvTitle;
    RecyclerView rvCourseData;
    SubCategoryDetailListAdapter adapter;
    ArrayList<SubCategoryDetailData> subCategoryDetailData;

    public CourseFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        initViews(view);
        getData();

        return view;
    }

    public void initViews(View view){

        name = getArguments().getString("name");
        id = getArguments().getInt("id");
        ivBack = view.findViewById(R.id.ivBack);
        tvTitle = view.findViewById(R.id.tvTitle);
        rvCourseData = view.findViewById(R.id.rvCourseData);

        subCategoryDetailData = new ArrayList<SubCategoryDetailData>();

        tvTitle.setText(name);

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

        Call<SubCategoryDetailResponseModel> call = ApplicationClass.apiInterface.getSubCategoryDetail(id);
        call.enqueue(new Callback<SubCategoryDetailResponseModel>() {
            @Override
            public void onResponse(Call<SubCategoryDetailResponseModel> call, Response<SubCategoryDetailResponseModel> response) {

                if(response.body().getSuccess() == 1){
                    List<SubCategoryDetailResponseModel.Response> data = response.body().getResponse();

                    for(int i=0; i<data.size(); i++){
                        subCategoryDetailData.add(new SubCategoryDetailData(data.get(i).getId(),data.get(i).getSubCategoryId(),data.get(i).getRating(),data.get(i).getPeopleRated(),data.get(i).getCost(),data.get(i).getTitle(),data.get(i).getTag(),data.get(i).getInstructorName(),data.get(i).getImageUrl()));
                    }

                    adapter = new SubCategoryDetailListAdapter(getContext(), subCategoryDetailData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvCourseData.setLayoutManager(layoutManager);
                    rvCourseData.setAdapter(adapter);

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


    }
}

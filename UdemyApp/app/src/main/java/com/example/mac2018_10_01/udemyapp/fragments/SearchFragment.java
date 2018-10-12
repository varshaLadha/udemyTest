package com.example.mac2018_10_01.udemyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.ApplicationClass;
import com.example.mac2018_10_01.udemyapp.adapters.CategoryDataAdapter;
import com.example.mac2018_10_01.udemyapp.adapters.CategoryListAdapter;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    ImageView ivSearch, ivBack;
    EditText tvSearch;
    RecyclerView rvCourseList;
    CategoryListAdapter adapter;
    ArrayList<CategoryData> categoryData;

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews(view);
        getData();

        return view;
    }

    public void initViews(View view){

        ivSearch = view.findViewById(R.id.ivSearch);
        ivBack = view.findViewById(R.id.ivBack);
        tvSearch = view.findViewById(R.id.tvSearch);
        rvCourseList = view.findViewById(R.id.rvCourseList);

        categoryData = new ArrayList<CategoryData>();

        tvSearch.requestFocus();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSearch.clearFocus();
                tvSearch.setCursorVisible(false);
                ivBack.setVisibility(View.GONE);
                ivSearch.setVisibility(View.VISIBLE);
            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSearch.requestFocus();
                tvSearch.setCursorVisible(true);
                ivSearch.setVisibility(View.GONE);
                ivBack.setVisibility(View.VISIBLE);
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSearch.requestFocus();
                tvSearch.setCursorVisible(true);
                ivSearch.setVisibility(View.GONE);
                ivBack.setVisibility(View.VISIBLE);
            }
        });

        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getContext(), tvSearch.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

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

                    adapter = new CategoryListAdapter(getContext(), categoryData);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rvCourseList.setLayoutManager(layoutManager);
                    rvCourseList.setAdapter(adapter);
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

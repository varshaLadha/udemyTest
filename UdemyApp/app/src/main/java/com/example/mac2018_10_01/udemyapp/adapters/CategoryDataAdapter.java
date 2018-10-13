package com.example.mac2018_10_01.udemyapp.adapters;

import android.content.Context;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.BrowseActivity;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.MyViewHolder>{

    ArrayList<CategoryData> categoryDataList;
    CategoryData categoryData;
    Context context;

    public CategoryDataAdapter(Context context, ArrayList<CategoryData> categoryDataList){
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cource_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        categoryData = categoryDataList.get(position);

        Picasso.get()
                .load(categoryData.getImageUrl())
                .into(holder.ivCourseImage);
        holder.tvCourseName.setText(categoryData.getCourseName());

        holder.tvCourseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrowseActivity)context).loadCategories(categoryDataList.get(position).getId(),categoryDataList.get(position).getCourseName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivCourseImage;
        public TextView tvCourseName;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivCourseImage = itemView.findViewById(R.id.ivCourseImage);
            tvCourseName = itemView.findViewById(R.id.tvCourseName);
        }
    }
}

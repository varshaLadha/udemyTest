package com.example.mac2018_10_01.udemyapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.modalClasses.CategoryData;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder>{

    ArrayList<CategoryData> categoryDataList;
    CategoryData categoryData;
    Context context;

    public CategoryListAdapter(Context context, ArrayList<CategoryData> categoryDataList){
        this.context = context;
        this.categoryDataList = categoryDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.MyViewHolder holder, final int position) {
        categoryData = categoryDataList.get(position);

        String catName[] = categoryData.getCourseName().toLowerCase().split(" ");
        String category = catName[0];

        int drawableId = context.getResources().getIdentifier(category, "drawable", context.getPackageName());
        holder.ivCategoryLogoImage.setImageResource(drawableId);
        holder.tvCategoryName.setText(categoryData.getCourseName());

        holder.llCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, categoryDataList.get(position).getId() + " " + categoryDataList.get(position).getCourseName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCategoryLogoImage;
        public TextView tvCategoryName;
        public LinearLayout llCategory;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivCategoryLogoImage = itemView.findViewById(R.id.ivLogo);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            llCategory = itemView.findViewById(R.id.llCategory);
        }
    }
}

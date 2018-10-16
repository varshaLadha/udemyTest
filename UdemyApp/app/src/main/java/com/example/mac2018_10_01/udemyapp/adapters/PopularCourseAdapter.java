package com.example.mac2018_10_01.udemyapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularCourseAdapter extends RecyclerView.Adapter<PopularCourseAdapter.MyViewHolder>{

    ArrayList<SubCategoryDetailData> subCategoryDetailDataList;
    SubCategoryDetailData subCategoryDetailData;
    Context context;

    public PopularCourseAdapter(Context context, ArrayList<SubCategoryDetailData> subCategoryDetailDataList){
        this.context = context;
        this.subCategoryDetailDataList = subCategoryDetailDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_courses, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        subCategoryDetailData = subCategoryDetailDataList.get(position);

        Picasso.get()
                .load(subCategoryDetailData.getImageUrl())
                .into(holder.ivCoverImage);
        holder.tvTitle.setText(subCategoryDetailData.getTitle());
        holder.rbRating.setRating(subCategoryDetailData.getAverageRating());
        holder.tvRating.setText("("+subCategoryDetailData.getPeopleRated()+")");
        holder.tvCost.setText("Rs. "+subCategoryDetailData.getCost());

    }

    @Override
    public int getItemCount() {
        return subCategoryDetailDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cvPopularCourses;
        public ImageView ivCoverImage;
        public TextView tvTitle, tvRating, tvCost;
        public RatingBar rbRating;

        public MyViewHolder(View itemView) {
            super(itemView);

            cvPopularCourses = itemView.findViewById(R.id.cvPopularCourses);
            ivCoverImage = itemView.findViewById(R.id.ivCoverImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvCost = itemView.findViewById(R.id.tvCost);
            rbRating = itemView.findViewById(R.id.rbRating);
        }
    }
}

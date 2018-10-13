package com.example.mac2018_10_01.udemyapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryDetailData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubCategoryDetailListAdapter extends RecyclerView.Adapter<SubCategoryDetailListAdapter.MyViewHolder>{

    ArrayList<SubCategoryDetailData> subCategoryDetailDataList;
    SubCategoryDetailData subCategoryDetailData;
    Context context;

    public SubCategoryDetailListAdapter(Context context, ArrayList<SubCategoryDetailData> subCategoryDetailDataList){
        this.context = context;
        this.subCategoryDetailDataList = subCategoryDetailDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_data, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        subCategoryDetailData = subCategoryDetailDataList.get(position);

        Picasso.get()
                .load(subCategoryDetailData.getImageUrl())
                .into(holder.ivImage);
        holder.tvTitle.setText(subCategoryDetailData.getTitle());

        if(subCategoryDetailData.getTag() != null){
            holder.tvTag.setVisibility(View.VISIBLE);
            holder.tvTag.setText(subCategoryDetailData.getTag());
        }

        double rating = (double) subCategoryDetailData.getRating()/(subCategoryDetailData.getPeopleRated()*5);
        String ratingAvg = String.format("%.2f",rating);

        holder.tvAuthor.setText(subCategoryDetailData.getInstructorName());
        holder.tvRating.setText(ratingAvg+" ("+subCategoryDetailData.getPeopleRated()+")");
        holder.tvCost.setText("Rs. "+subCategoryDetailData.getCost());

    }

    @Override
    public int getItemCount() {
        return subCategoryDetailDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cvViewHolder;
        public ImageView ivImage;
        public TextView tvTitle, tvTag, tvAuthor, tvRating, tvCost;

        public MyViewHolder(View itemView) {
            super(itemView);

            cvViewHolder = itemView.findViewById(R.id.cvViewHolder);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvCost = itemView.findViewById(R.id.tvCost);
        }
    }
}

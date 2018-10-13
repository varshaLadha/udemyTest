package com.example.mac2018_10_01.udemyapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.activity.BrowseActivity;
import com.example.mac2018_10_01.udemyapp.modalClasses.SubCategoryData;

import java.util.ArrayList;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.MyViewHolder>{

    ArrayList<SubCategoryData> subCategoryDataList;
    SubCategoryData subCategoryData;
    Context context;

    public SubCategoryListAdapter(Context context, ArrayList<SubCategoryData> subCategoryDataList){
        this.context = context;
        this.subCategoryDataList = subCategoryDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        subCategoryData = subCategoryDataList.get(position);

        String subCategoryName = subCategoryData.getSubCategoryName().toLowerCase().replace(" ","_");

        int drawableId = context.getResources().getIdentifier(subCategoryName, "drawable", context.getPackageName());
        holder.ivCategoryLogoImage.setImageResource(drawableId);
        holder.tvCategoryName.setText(subCategoryData.getSubCategoryName());

        holder.llCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BrowseActivity)context).loadCategoryData(subCategoryDataList.get(position).getId(), subCategoryDataList.get(position).getSubCategoryName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return subCategoryDataList.size();
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

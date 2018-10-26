package com.example.mac2018_10_01.udemyapp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.fragments.AccountFragment;
import com.example.mac2018_10_01.udemyapp.fragments.CourseFragment;
import com.example.mac2018_10_01.udemyapp.fragments.FeaturedFragment;
import com.example.mac2018_10_01.udemyapp.fragments.MyCourseFragment;
import com.example.mac2018_10_01.udemyapp.fragments.SearchFragment;
import com.example.mac2018_10_01.udemyapp.fragments.SearchedDataFragment;
import com.example.mac2018_10_01.udemyapp.fragments.SubCategoryFragment;
import com.example.mac2018_10_01.udemyapp.fragments.WishlistFragment;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserData;
import com.google.gson.Gson;

public class BrowseActivity extends AppCompatActivity {

    Gson gson;
    String object;
    UserData userData;
    ImageView ivFeature, ivSearch, ivMyCourse, ivWishlist, ivAccount;
    TextView tvFeature, tvSearch, tvMyCourse, tvWishlist, tvAccount;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        initViews();
    }

    public void initViews(){

        ab = getSupportActionBar();
        ab.hide();

        ivFeature = (ImageView)findViewById(R.id.ivFeature);
        ivSearch = (ImageView)findViewById(R.id.ivSearch);
        ivMyCourse = (ImageView)findViewById(R.id.ivMyCourse);
        ivWishlist = (ImageView)findViewById(R.id.ivWishlist);
        ivAccount = (ImageView)findViewById(R.id.ivAccount);

        tvFeature = (TextView)findViewById(R.id.tvFeature);
        tvSearch = (TextView)findViewById(R.id.tvSearch);
        tvMyCourse = (TextView)findViewById(R.id.tvMyCourse);
        tvWishlist = (TextView)findViewById(R.id.tvWishlist);
        tvAccount = (TextView)findViewById(R.id.tvAccount);

        if(ApplicationClass.pref.contains("userDetail")){
            gson=new Gson();
            object=ApplicationClass.pref.getString("userDetail","");
            userData=gson.fromJson(object,UserData.class);
        }

        loadFragment(new FeaturedFragment());
        ab.setTitle("Featured");
        ivFeature.setImageResource(R.drawable.feature_focused);
        tvFeature.setTextColor(getResources().getColor(R.color.white));
    }

    public void onClick(View v){
        Fragment fragment;

        switch (v.getId()){
            case R.id.llFeature:
                setFocused(R.drawable.feature_focused,getResources().getColor(R.color.white),R.drawable.search,getResources().getColor(R.color.lightGrey),R.drawable.play,getResources().getColor(R.color.lightGrey),R.drawable.heart,getResources().getColor(R.color.lightGrey),R.drawable.profile,getResources().getColor(R.color.lightGrey));

                fragment = new FeaturedFragment();
                loadFragment(fragment);
                break;

            case R.id.llSearch:
                setFocused(R.drawable.feature,getResources().getColor(R.color.lightGrey),R.drawable.search_focused,getResources().getColor(R.color.white),R.drawable.play,getResources().getColor(R.color.lightGrey),R.drawable.heart,getResources().getColor(R.color.lightGrey),R.drawable.profile,getResources().getColor(R.color.lightGrey));

                fragment = new SearchFragment();
                loadFragment(fragment);
                break;

            case R.id.llMyCourse:
                setFocused(R.drawable.feature,getResources().getColor(R.color.lightGrey),R.drawable.search,getResources().getColor(R.color.lightGrey),R.drawable.play_focused,getResources().getColor(R.color.white),R.drawable.heart,getResources().getColor(R.color.lightGrey),R.drawable.profile,getResources().getColor(R.color.lightGrey));

                fragment = new MyCourseFragment();
                loadFragment(fragment);
                break;

            case R.id.llWishlist:
                setFocused(R.drawable.feature,getResources().getColor(R.color.lightGrey),R.drawable.search,getResources().getColor(R.color.lightGrey),R.drawable.play,getResources().getColor(R.color.lightGrey),R.drawable.heart_focused,getResources().getColor(R.color.white),R.drawable.profile,getResources().getColor(R.color.lightGrey));

                fragment = new WishlistFragment();
                loadFragment(fragment);
                break;

            case R.id.llAccount:
                setFocused(R.drawable.feature,getResources().getColor(R.color.lightGrey),R.drawable.search,getResources().getColor(R.color.lightGrey),R.drawable.play,getResources().getColor(R.color.lightGrey),R.drawable.heart,getResources().getColor(R.color.lightGrey),R.drawable.profile_focused,getResources().getColor(R.color.white));

                fragment = new AccountFragment();
                loadFragment(fragment);
                break;
        }
    }

    public void setFocused(int featureDrawable, int featureColor,
                           int searchDrawable, int searchColor,
                           int myCourseDrawable, int myCourseColor,
                           int wishlistDrawable, int wishlistColor,
                           int accountDrawable, int accountColor){
        ivFeature.setImageResource(featureDrawable);
        tvFeature.setTextColor(featureColor);

        ivSearch.setImageResource(searchDrawable);
        tvSearch.setTextColor(searchColor);

        ivMyCourse.setImageResource(myCourseDrawable);
        tvMyCourse.setTextColor(myCourseColor);

        ivWishlist.setImageResource(wishlistDrawable);
        tvWishlist.setTextColor(wishlistColor);

        ivAccount.setImageResource(accountDrawable);
        tvAccount.setTextColor(accountColor);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }

    public void loadCategories(int id,String subcategory){

        Bundle bundle = new Bundle();
        bundle.putString("subCategory", subcategory);
        bundle.putInt("id",id);

        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadCategoryData( int id, String name){
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putInt("id",id);

        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void loadSearchedData(int id, String searchKey, Boolean isSearch) {

        Bundle bundle = new Bundle();
        bundle.putString("searchKey", searchKey);
        bundle.putInt("id",id);
        bundle.putBoolean("isSearch", isSearch);

        SearchedDataFragment fragment = new SearchedDataFragment();
        fragment.setArguments(bundle);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

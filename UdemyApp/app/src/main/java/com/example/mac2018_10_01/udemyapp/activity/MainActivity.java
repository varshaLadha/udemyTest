package com.example.mac2018_10_01.udemyapp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ApplicationClass.pref.contains("userDetail")){
            Intent intent=new Intent(MainActivity.this,BrowseActivity.class);
            startActivity(intent);
            finish();
        }

        initViews();
    }

    public void initViews(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ab=getSupportActionBar();
        ab.hide();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBrowse:
                Intent intentBrowse = new Intent(MainActivity.this, BrowseActivity.class);
                startActivity(intentBrowse);
                break;
            case R.id.btnSignIn:
                Intent intentSignin = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intentSignin);
                break;
        }
    }
}

package com.example.mac2018_10_01.udemyapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mac2018_10_01.udemyapp.R;

public class LoginSplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    ActionBar ab;
    ImageView ivCheck;
    Animation zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash);

        ab=getSupportActionBar();
        ab.hide();

        zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        ivCheck = (ImageView)findViewById(R.id.ivCheck);
        ivCheck.setAnimation(zoom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoginSplashActivity.this, BrowseActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}


/**
 *
 *

 10-10-2018 Update
 - Complete with designing of registration page
 - Working with validations at time of registering new user
 - Create back end for user table with user login and register route
 - Handle google login for user


 11-10-2018 Update
 - Completed with validations at time of registration and login of user
 - Completed with invoking user sign-in and sign-up routes
 - Created necessary tabs as per app
 - Working on account tab

 12-10-2018 Update
 - Create back end for category and sub-category with necessary routes
 - Working with account tab various link click
 - Designing layout for Wishlist tab

 13-10-2018's Update
 - Working with fetching category and sub-category data and displaying
 - Done with managing click of category image click to display sub-categories
 - Design database table for courses and created necessary routes

 Today's Update
 - Populate database with data for course table
 - Completed with fetching popular courses based on rating for particular Category (Backend as well as frontend)
 - Working with featured tab

 *
 * **/
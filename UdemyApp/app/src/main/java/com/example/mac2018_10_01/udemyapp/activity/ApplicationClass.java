package com.example.mac2018_10_01.udemyapp.activity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.mac2018_10_01.udemyapp.api.ApiClient;
import com.example.mac2018_10_01.udemyapp.api.ApiInterface;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ApplicationClass extends Application {

    public static ApiInterface apiInterface;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;
    public static GoogleSignInClient mGoogleSignInClient;

    @Override
    public void onCreate() {
        super.onCreate();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        pref=getApplicationContext().getSharedPreferences("ah_firebase", Context.MODE_PRIVATE);
        editor=pref.edit();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
}

package com.example.mac2018_10_01.udemyapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserData;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserResponseModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    ActionBar ab;
    LinearLayout signIn, llGoogleSignin;
    TextView tvCreateAccount, tvTerms;
    private static final int RC_SIGN_IN = 007;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initViews();
    }

    public void initViews() {
        ab=getSupportActionBar();
        ab.hide();

        signIn = (LinearLayout) findViewById(R.id.llEmailSignin);
        llGoogleSignin = (LinearLayout)findViewById(R.id.llGoogleSignin);
        tvCreateAccount = (TextView)findViewById(R.id.tvCreateAccount);
        tvTerms = (TextView)findViewById(R.id.tvTerms);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        tvTerms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.udemy.com/terms/"));
                startActivity(browserIntent);
            }
        });

        llGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = ApplicationClass.mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Call<UserResponseModel> call = ApplicationClass.apiInterface.userLoginGoogle(account.getDisplayName(), account.getEmail(), "google", false);
            call.enqueue(new Callback<UserResponseModel>() {
                @Override
                public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                    UserResponseModel userModel = response.body();
                    try{
                        if(userModel.getSuccess() == 1){
                            UserResponseModel.Response response1 = userModel.getResponse();
                            UserData userData = new UserData(response1.getUserName(), response1.getEmail(), response1.getLoginType());
                            Gson gson=new Gson();
                            String object=gson.toJson(userData);

                            ApplicationClass.editor.putString("userDetail", object);
                            ApplicationClass.editor.commit();

                            Intent intent = new Intent(SigninActivity.this, LoginSplashActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else {
                            Log.d( "onResponse: Else",userModel.getResponse().getError()+"");
                            Toast.makeText(SigninActivity.this, userModel.getResponse().getError(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(SigninActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e( "onResponse: ", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<UserResponseModel> call, Throwable t) {
                    Toast.makeText(SigninActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e( "onFailure: ", t.getMessage());
                }
            });
        } catch (ApiException e) {
            Log.w("Log", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}

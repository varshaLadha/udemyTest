package com.example.mac2018_10_01.udemyapp.activity;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;
import com.example.mac2018_10_01.udemyapp.api.ApiClient;
import com.example.mac2018_10_01.udemyapp.api.ApiInterface;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserData;
import com.example.mac2018_10_01.udemyapp.modalClasses.UserResponseModel;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity {

    ActionBar ab;
    ImageView ivShowHide;
    TextInputEditText etEmail, etPassword;
    boolean show = false;
    Button btSignIn;
    String email, password;
    TextInputLayout inputEmail;
    TextView tvMessage, tvMessage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        initViews();
    }

    public void initViews() {
        ab=getSupportActionBar();
        ab.hide();

        etEmail = (TextInputEditText)findViewById(R.id.etEmail);
        etPassword = (TextInputEditText)findViewById(R.id.etPassword);
        inputEmail = (TextInputLayout)findViewById(R.id.inputEmail);
        ivShowHide = (ImageView)findViewById(R.id.ivShowHide);
        btSignIn = (Button)findViewById(R.id.btSignIn);
        tvMessage = (TextView)findViewById(R.id.tvMessage);
        tvMessage1 = (TextView)findViewById(R.id.tvMessage1);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        etEmail.setText(email);

        ivShowHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!show){
                    ivShowHide.setImageResource(R.drawable.view);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show = true;
                }else {
                    ivShowHide.setImageResource(R.drawable.hide);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show = false;
                }
            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                login(email, password);
            }
        });
    }

    public void login(String email, String password){
        Log.d( "login: ",email+ " "+password);
        Call<UserResponseModel> call = ApplicationClass.apiInterface.userLoginEmail(email, password, "email");
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

                        Intent intent = new Intent(PasswordActivity.this, LoginSplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        tvMessage.setText("Oops!");
                        tvMessage1.setVisibility(View.GONE);
                        inputEmail.setVisibility(View.VISIBLE);
                        Log.d( "onResponse: Else",userModel.getResponse().getError()+"");
                        Toast.makeText(PasswordActivity.this, userModel.getResponse().getError(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(PasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e( "onResponse: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponseModel> call, Throwable t) {
                Toast.makeText(PasswordActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e( "onFailure: ", t.getMessage());
            }
        });
    }
}

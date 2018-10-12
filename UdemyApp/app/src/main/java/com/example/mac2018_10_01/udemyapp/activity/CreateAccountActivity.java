package com.example.mac2018_10_01.udemyapp.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
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

public class CreateAccountActivity extends AppCompatActivity {

    ActionBar ab;
    TextInputEditText etName, etEmail,etPassword;
    TextView tvNameRequired, tvEmailRequired, tvPasswordRequired, tvHaveAcccount;
    ImageView ivShowHide;
    Button btCreateAccount;
    String name, email, password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    LinearLayout llGoogleSignin;
    private static final int RC_SIGN_IN = 007;
    boolean show = false;
    boolean emailDeals;
    CheckBox chkEmailDeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initViews();
    }

    public void initViews(){
        ab=getSupportActionBar();
        ab.hide();

        etName = (TextInputEditText)findViewById(R.id.etName);
        etEmail = (TextInputEditText)findViewById(R.id.etEmail);
        etPassword = (TextInputEditText)findViewById(R.id.etPassword);
        tvNameRequired = (TextView)findViewById(R.id.tvNameRequired);
        tvEmailRequired = (TextView)findViewById(R.id.tvEmailRequired);
        tvPasswordRequired = (TextView)findViewById(R.id.tvPasswordRequired);
        tvHaveAcccount = (TextView)findViewById(R.id.tvHaveAccount);
        ivShowHide = (ImageView)findViewById(R.id.ivShowHide);
        btCreateAccount = (Button)findViewById(R.id.btCreateAccount);
        llGoogleSignin = (LinearLayout)findViewById(R.id.llGoogleSignin);
        chkEmailDeals = (CheckBox)findViewById(R.id.chkEmailDeals);

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

        tvHaveAcccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvNameRequired.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvEmailRequired.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvPasswordRequired.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString();
                if(chkEmailDeals.isChecked()){
                    emailDeals = true;
                }else {
                    emailDeals = false;
                }

                if(name.length() == 0){
                    tvNameRequired.setVisibility(View.VISIBLE);
                }
                if(email.length() == 0){
                    tvEmailRequired.setVisibility(View.VISIBLE);
                    tvEmailRequired.setText(R.string.requiredField);
                }else if(!email.matches(emailPattern)){
                    tvEmailRequired.setVisibility(View.VISIBLE);
                    tvEmailRequired.setText(R.string.validEmail);
                }
                if(password.length() == 0){
                    tvPasswordRequired.setText(R.string.requiredField);
                    tvPasswordRequired.setVisibility(View.VISIBLE);
                } else if(password.length() < 6){
                    tvPasswordRequired.setText(R.string.passwordLength);
                    tvPasswordRequired.setVisibility(View.VISIBLE);
                }

                if(name.length() > 0 && email.matches(emailPattern) && password.length() > 5){
                    Call<UserResponseModel> call = ApplicationClass.apiInterface.userSignUp(name, email, password, "email",emailDeals);
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

                                    Intent intent = new Intent(CreateAccountActivity.this, LoginSplashActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Log.d( "onResponse: Else",userModel.getResponse().getError()+"");
                                    Toast.makeText(CreateAccountActivity.this, userModel.getResponse().getError(), Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(CreateAccountActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e( "onResponse: ", e.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponseModel> call, Throwable t) {
                            Toast.makeText(CreateAccountActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e( "onFailure: ", t.getMessage());
                        }
                    });
                }

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

                            Intent intent = new Intent(CreateAccountActivity.this, LoginSplashActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else {
                            Log.d( "onResponse: Else",userModel.getResponse().getError()+"");
                            Toast.makeText(CreateAccountActivity.this, userModel.getResponse().getError(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(CreateAccountActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e( "onResponse: ", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<UserResponseModel> call, Throwable t) {
                    Toast.makeText(CreateAccountActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e( "onFailure: ", t.getMessage());
                }
            });
        } catch (ApiException e) {
            Log.w("Log", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}

package com.example.mac2018_10_01.udemyapp.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac2018_10_01.udemyapp.R;

public class EmailActivity extends AppCompatActivity {

    ActionBar ab;
    TextInputEditText edtEmail;
    Button btnNext;
    String email;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView tvErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        initViews();
    }

    public void initViews() {
        ab=getSupportActionBar();
        ab.hide();

        edtEmail = (TextInputEditText)findViewById(R.id.etEmail);
        btnNext = (Button)findViewById(R.id.btNext);
        tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);

        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvErrorMessage.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString().trim();

                if(!email.matches(emailPattern)){
                    tvErrorMessage.setVisibility(View.VISIBLE);
                }else {
                    Intent intent = new Intent(EmailActivity.this, PasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

            }
        });
    }
}

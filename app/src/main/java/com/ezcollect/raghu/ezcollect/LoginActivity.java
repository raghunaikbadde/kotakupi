
package com.ezcollect.raghu.ezcollect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import preferences.KotakPreferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mPassWordEt;
    private EditText mUserNameEt;
    private ImageView mLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
        registerEvents();
    }

    private void registerEvents() {
        mUserNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userName = s.toString();

                if (userName.equalsIgnoreCase("Raghu") && mPassWordEt.getText().toString().trim().equalsIgnoreCase("123456")) {
                    mLogin.setImageResource(R.drawable.proceed_active);
                    mLogin.setEnabled(true);
                } else {
                    mLogin.setEnabled(false);
                    mLogin.setImageResource(R.drawable.proceed_inactive);
                }
//                if (password.length() >= 6 && mPassWordEt.getText().toString().trim().length()>=6) {
//                    mLogin.setImageResource(R.drawable.proceed_active);
//                } else {
//                    mLogin.setImageResource(R.drawable.proceed_inactive);
//                }
            }
        });
        mPassWordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                if (password.equalsIgnoreCase("123456") && mUserNameEt.getText().toString().trim().equalsIgnoreCase("Raghu")) {
                    mLogin.setEnabled(true);
                    mLogin.setImageResource(R.drawable.proceed_active);
                } else {
                    mLogin.setEnabled(false);
                    mLogin.setImageResource(R.drawable.proceed_inactive);
                }
//                if (password.length() >= 6 && mUserNameEt.getText().toString().trim().length()>=6) {
//                    mLogin.setImageResource(R.drawable.proceed_active);
//                } else {
//                    mLogin.setImageResource(R.drawable.proceed_inactive);
//                }
            }
        });
        mLogin.setOnClickListener(this);
    }

    private void initViews() {
        mUserNameEt = (EditText) findViewById(R.id.user_name);
        mPassWordEt = (EditText) findViewById(R.id.user_pass);
        mLogin = (ImageView) findViewById(R.id.login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                KotakPreferences.getInstance(LoginActivity.this).putdata("isLogin", true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

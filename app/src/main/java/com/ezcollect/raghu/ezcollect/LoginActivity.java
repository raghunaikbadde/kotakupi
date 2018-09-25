
package com.ezcollect.raghu.ezcollect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
                String password = s.toString();
                if (password.length() >= 6 && mPassWordEt.getText().toString().trim().length()>=6) {
                    mLogin.setImageResource(R.drawable.proceed_active);
                } else {
                    mLogin.setImageResource(R.drawable.proceed_inactive);
                }
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
                if (password.length() >= 6 && mUserNameEt.getText().toString().trim().length()>=6) {
                    mLogin.setImageResource(R.drawable.proceed_active);
                } else {
                    mLogin.setImageResource(R.drawable.proceed_inactive);
                }
            }
        });
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

                break;
        }
    }
}

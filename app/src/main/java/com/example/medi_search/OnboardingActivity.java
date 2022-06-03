package com.example.medi_search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signUp) Button mSignUp;
    @BindView(R.id.userName) EditText mUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String userName = mUserName.getText().toString();
        Toast.makeText(OnboardingActivity.this, "Welcome to Medi-Search " + userName, Toast.LENGTH_LONG).show();
    }
}
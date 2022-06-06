package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medi_search.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signUp) Button mSignUp;
    @BindView(R.id.userName) EditText mUserName;
    @BindView(R.id.directToSignIn) Button mdirectToSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mSignUp.setOnClickListener(this);
        mdirectToSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if (v == mSignUp ) {
           String userName = mUserName.getText().toString();
           Toast.makeText(OnboardingActivity.this, "Welcome to Medi-Search " + userName, Toast.LENGTH_LONG).show();
           Intent intent = new Intent(OnboardingActivity.this, StartAssessmentActivity.class);

           intent.putExtra("userName", userName);
           startActivity(intent);
       }

       if (v == mdirectToSignIn) {
           Intent intent = new Intent(OnboardingActivity.this, SignInActivity.class);
           startActivity(intent);
       }
    }
}
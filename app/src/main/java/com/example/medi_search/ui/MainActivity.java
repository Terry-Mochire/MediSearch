package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.medi_search.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.homeSignUp) Button mHomeSignUp;
    @BindView(R.id.homeSignIn) Button mHomeSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHomeSignUp.setOnClickListener(this);
        mHomeSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if ( v == mHomeSignUp){
           Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
           startActivity(intent);
       }

       if ( v == mHomeSignIn) {
           Intent intent = new Intent(MainActivity.this, SignInActivity.class);
           startActivity(intent);
       }
    }
}
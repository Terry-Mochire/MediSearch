package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.medi_search.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.askToRegister)
    TextView mAskToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Intent intent = getIntent();
        ButterKnife.bind(this);
        mAskToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAskToRegister) {
            Intent intent = new Intent(SignInActivity.this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
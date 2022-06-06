package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.medi_search.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartAssessmentActivity extends AppCompatActivity {

    @BindView(R.id.helloUser) TextView mhelloUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_assessment);
        Intent intent = getIntent();
        ButterKnife.bind(this);
        String userName = intent.getStringExtra("userName");
        mhelloUser.setText("Hello " + userName);
        Log.i("Username", userName);

    }
}
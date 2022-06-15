package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.medi_search.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartAssessmentActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.helloUser) TextView mhelloUser;
    @BindView(R.id.StartAssessment) Button mStartAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_assessment);
        Intent intent = getIntent();
        ButterKnife.bind(this);
//        String userName = intent.getStringExtra("userName");
//        mhelloUser.setText("Hello " + userName);
//        Log.i("Username", userName);

        mStartAssessment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent startIntent = new Intent(StartAssessmentActivity.this, AddSymptomsActivity.class);
        startActivity(startIntent);
    }
}
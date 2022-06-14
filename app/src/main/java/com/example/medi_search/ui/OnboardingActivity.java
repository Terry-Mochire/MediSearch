package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medi_search.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signUp) Button mSignUp;
    @BindView(R.id.userName) EditText mUserName;
    @BindView(R.id.userEmail) EditText mUserEmail;
    @BindView(R.id.userPassword) EditText mUserPassword;
    @BindView(R.id.userConfirmPassword) EditText mUserConfirmPassword;
    @BindView(R.id.directToSignIn) Button mdirectToSignIn;

    private FirebaseAuth mAuth;
    public static final String TAG = OnboardingActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        mAuth = FirebaseAuth.getInstance();

        mSignUp.setOnClickListener(this);
        mdirectToSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if (v == mSignUp ) {
           createNewUser();
           String userName = mUserName.getText().toString();
           Toast.makeText(OnboardingActivity.this, "Welcome to Medi-Search " + userName, Toast.LENGTH_LONG).show();
           Intent intent = new Intent(OnboardingActivity.this, StartAssessmentActivity.class);

           intent.putExtra("userName", userName);
           startActivity(intent);
       }

       if (v == mdirectToSignIn) {
           Intent intent = new Intent(OnboardingActivity.this, SignInActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(intent);
           finish();
       }
    }

    private void createNewUser() {
        final String name = mUserName.getText().toString().trim();
        final String email = mUserEmail.getText().toString().trim();
        String password = mUserPassword.getText().toString().trim();
        String confirmPassword = mUserConfirmPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication is successful!");
                    } else {
                        Toast.makeText(OnboardingActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
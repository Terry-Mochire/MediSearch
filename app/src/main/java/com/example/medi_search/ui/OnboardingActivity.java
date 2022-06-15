package com.example.medi_search.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medi_search.Constants;
import com.example.medi_search.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnboardingActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.signUp) Button mSignUp;
    @BindView(R.id.userName) EditText mUserName;
    @BindView(R.id.userEmail) EditText mUserEmail;
    @BindView(R.id.userPassword) EditText mUserPassword;
    @BindView(R.id.userConfirmPassword) EditText mUserConfirmPassword;
    @BindView(R.id.directToSignIn) Button mdirectToSignIn;

    private String mName;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private FirebaseAuth mAuth;
    public static final String TAG = OnboardingActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);
        Intent intent = getIntent();


        mAuth = FirebaseAuth.getInstance();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSignUp.setOnClickListener(this);
        mdirectToSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if (v == mSignUp ) {
           createNewUser();

       }

       if (v == mdirectToSignIn) {
           Intent intent = new Intent(OnboardingActivity.this, SignInActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(intent);
           finish();
       }
    }

    private void addToSharedPreferences(String username) {
        mEditor.putString(Constants.PREFERENCES_USERNAME_KEY, username).apply();
    }

    private void createNewUser() {
        final String name = mUserName.getText().toString().trim();
        final String email = mUserEmail.getText().toString().trim();
        String password = mUserPassword.getText().toString().trim();
        String confirmPassword = mUserConfirmPassword.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;

        String username = mUserName.getText().toString();
        addToSharedPreferences(username);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication is successful!");
                        createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                    } else {
                        Toast.makeText(OnboardingActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });

        Toast.makeText(OnboardingActivity.this, "Welcome to Medi-Search " + username, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(OnboardingActivity.this, StartAssessmentActivity.class);
        startActivity(intent);
    }

    private void createFirebaseUserProfile(final FirebaseUser user) {
        mName = mUserName.getText().toString().trim();
        Log.d(TAG, "Username: " + mName);

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, Objects.requireNonNull(user.getDisplayName()));
                            Toast.makeText(OnboardingActivity.this, "The display name has ben set", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mUserEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
           mUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mUserPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
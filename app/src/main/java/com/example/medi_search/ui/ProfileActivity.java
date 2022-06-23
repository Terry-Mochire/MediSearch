package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.example.medi_search.Constants;
import com.example.medi_search.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.firebaseEmail) TextView mEmail;
    @BindView(R.id.firebaseGender) TextView mGender;
    @BindView(R.id.fullname_field) TextView mFullname;
    @BindView(R.id.username_field) TextView mUsernameField;
    @BindView(R.id.firebaseSymptoms) TextView mSymptoms;
    @BindView(R.id.Diagnosis) TextView mDiagnosis;


    private DatabaseReference mPatientReference;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mPatientReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PATIENT)
                .child(uid);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String gender = mSharedPreferences.getString(Constants.PREFERENCES_USERGENDER_KEY, null);
        String userName = mSharedPreferences.getString(Constants.PREFERENCES_USERNAME_KEY, null);
        String userEmail = mSharedPreferences.getString(Constants.PREFERENCES_USEREMAIL_KEY, null);
        String userSymptom = mSharedPreferences.getString(Constants.PREFERENCES_USERSYMPTOM_KEY, null);

        mGender.setText(gender);
        mFullname.setText(userName);
        mEmail.setText(userEmail);
        mSymptoms.setText(userSymptom);
        mDiagnosis.setText("Working on your Diagnosis");
        mUsernameField.setText(userName);

    }
}
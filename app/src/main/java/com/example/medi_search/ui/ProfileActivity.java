package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.medi_search.Constants;
import com.example.medi_search.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.firebaseEmail) TextView mEmail;


    private DatabaseReference mPatientReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mPatientReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PATIENT)
                .child(uid);


    }
}
package com.example.medi_search.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
    @BindView(R.id.profile_image) ImageView mProfileImage;


    private DatabaseReference mPatientReference;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final int REQUEST_IMAGE_CAPTURE = 111;

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


        if (ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_photo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }



    public void onLaunchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            mProfileImage.setImageBitmap(captureImage);
        }
    }

    //
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            mImageLabel.setImageBitmap(imageBitmap);
//            //      encodeBitmapAndSaveToFirebase(imageBitmap);
//        }
//    }
}
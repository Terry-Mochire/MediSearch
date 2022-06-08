package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.medi_search.R;
import com.example.medi_search.models.Symptom;
import com.example.medi_search.network.Api;
import com.example.medi_search.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSymptomsActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.addSymptom)  AutoCompleteTextView mAddSymptom;
    @BindView(R.id.submitButton) Button msubmitButton;
    @BindView(R.id.addedSymptom) TextView selectedSymptom;
    public List<Symptom> allSymptoms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        Api client = ApiClient.getClient();
        Call<List<Symptom>> call = client.getSymptoms(25);

        call.enqueue(new Callback<List<Symptom>> () {

            @Override
            public void onResponse(Call<List<Symptom>>  call, Response<List<Symptom>>  response) {
                Log.i("Success Message", "Response is Successful");
                allSymptoms = response.body();
                ArrayList listOfSymptomNames = new ArrayList();
                for (int i = 0; i <= (allSymptoms.size() - 1); i++) {
                    String symptomName = allSymptoms.get(i).getCommonName();
                    listOfSymptomNames.add(symptomName);
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(AddSymptomsActivity.this, android.R.layout.select_dialog_item,listOfSymptomNames );
                mAddSymptom.setThreshold(1);
                mAddSymptom.setAdapter(arrayAdapter);

                msubmitButton.setOnClickListener(AddSymptomsActivity.this);

            }

            @Override
            public void onFailure(Call<List<Symptom>> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }

        });
    }

    @Override
    public void onClick(View v) {
        if( v == msubmitButton) {
            String text = mAddSymptom.getText().toString();
            selectedSymptom.setText(text);
            selectedSymptom.setVisibility(View.VISIBLE);
        }
    }
}
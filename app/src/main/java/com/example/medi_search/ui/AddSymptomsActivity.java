package com.example.medi_search.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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

public class AddSymptomsActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.addSymptom)  AutoCompleteTextView mAddSymptom;
    @BindView(R.id.submitButton) Button msubmitButton;
    @BindView(R.id.addedSymptom) TextView selectedSymptom;
    @BindView(R.id.gender_spinner) Spinner mGenderSpinner;
    public List<Symptom> allSymptoms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptoms);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(adapter);
        mGenderSpinner.setOnItemSelectedListener(this);

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
                msubmitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( v == msubmitButton) {
                            String text = mAddSymptom.getText().toString();
                            selectedSymptom.setText(text);
                            selectedSymptom.setVisibility(View.VISIBLE);

                            int index = listOfSymptomNames.indexOf(mAddSymptom.getText().toString());
                            String symptomId = allSymptoms.get(index).getId();
                            Log.d("Symptom id", symptomId);
                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Symptom>> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
            }

        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mGenderSpinner) {
        long gender =  parent.getItemIdAtPosition(position);
            parent.getPositionForView(view);

            Log.d("gender", String.valueOf(gender));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
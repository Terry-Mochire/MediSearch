package com.example.medi_search.network;



import com.example.medi_search.models.Symptom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("symptoms")
    Call<List<Symptom>>  getSymptoms(
            @Query("age.value") Integer age
    );
}

package com.example.ibrahimsaad.madarsofttask.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("data/2.5/forecast")
    Call<JsonObject> fetchWeatherData(@Query("lat") double lat,
                                 @Query("lon") double lon,
                                 @Query("cnt") int cnt);

}

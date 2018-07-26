package com.example.ibrahimsaad.madarsofttask.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.ibrahimsaad.madarsofttask.api.APIClient;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private static AppRepository ourInstance;
    private int count;
    public LiveData<List<WeatherEntity>> mWeather;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mWeather = getAllWeatherEntities();
    }

    private LiveData<List<WeatherEntity>> getAllWeatherEntities() {
        return mDb.weatherDao().getAll();
    }

    public void insertEntity(final WeatherEntity weatherEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.weatherDao().insertData(weatherEntity);
            }
        });
    }

    public void fetchData(double lat, double lon, int cnt) {

        APIClient.getClient().fetchWeatherData(lat, lon, cnt)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call,
                                           Response<JsonObject> response) {
                        insertEntity(new WeatherEntity(response.body().toString()));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
    }


}

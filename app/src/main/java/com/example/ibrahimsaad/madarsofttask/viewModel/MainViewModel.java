package com.example.ibrahimsaad.madarsofttask.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.ibrahimsaad.madarsofttask.database.AppRepository;
import com.example.ibrahimsaad.madarsofttask.database.WeatherEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<WeatherEntity>> mWeather;

    private AppRepository mRepository;

    public MutableLiveData<WeatherEntity> mLiveNote =
            new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mWeather = mRepository.mWeather;
    }

    public void insetEntity(String noteText) {
        WeatherEntity weatherEntity = mLiveNote.getValue();

        if (weatherEntity == null) {
            weatherEntity = new WeatherEntity(noteText);
        }
        mRepository.insertEntity(weatherEntity);

    }

    public  void fetchData(double lat , double lon , int cnt){
        mRepository.fetchData(lat, lon ,cnt);
    }

}

package com.example.ibrahimsaad.madarsofttask;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ibrahimsaad.madarsofttask.viewModel.MainViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private double lat;
    private double lon;
    public static final int NUM_OF_DIGITS = 4 ;
    MainViewModel mViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(13.0f);
        mMap.setMyLocationEnabled(true);
        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnCameraIdleListener(this);

    }

    @OnClick(R.id.addPlaceBtn)
    public void addPlaceBtnClick() {
        mViewModel.fetchData(lat,lon,3);

    }

    @Override
    public void onCameraIdle() {
        CameraPosition position = mMap.getCameraPosition();
        lat = Util.round(position.target.latitude,NUM_OF_DIGITS);
        lon = Util.round(position.target.longitude,NUM_OF_DIGITS);

    }
}

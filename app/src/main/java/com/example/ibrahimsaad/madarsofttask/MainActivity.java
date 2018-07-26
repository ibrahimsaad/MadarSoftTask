package com.example.ibrahimsaad.madarsofttask;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.ibrahimsaad.madarsofttask.api.APIClient;
import com.example.ibrahimsaad.madarsofttask.database.WeatherEntity;
import com.example.ibrahimsaad.madarsofttask.viewModel.MainViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private MainViewModel mViewModel;
    private List<WeatherEntity> weatherEntityList = new ArrayList<>();
    private WeatherRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();

    }

    private void initViewModel() {

        final Observer<List<WeatherEntity>> weatherObserver =
                new Observer<List<WeatherEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<WeatherEntity> weatherEntities) {
                        weatherEntityList.clear();
                        weatherEntityList.addAll(weatherEntities);
                        if (weatherEntities.isEmpty()){
                            mViewModel.fetchData(-34,105,3);
                        }

                        if (mAdapter == null) {
                            mAdapter = new WeatherRecyclerAdapter(weatherEntityList,
                                    MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                };

        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mViewModel.mWeather.observe(this, weatherObserver);
    }



    @OnClick(R.id.fabAddNewPlace)
    public void fabAddNewPlaceBtnClick() {
        MainActivityPermissionsDispatcher.enableLocationWithPermissionCheck(this);
    }

    @NeedsPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    public void enableLocation() {
        Intent mIntent = new Intent(this, MapsActivity.class);
        startActivity(mIntent);

    }

    @OnPermissionDenied(Manifest.permission.ACCESS_COARSE_LOCATION)
    void showPermissionExplain() {
        Toast.makeText(this,
                R.string.denied_permission_message,
                Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
                mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(divider);

    }
}

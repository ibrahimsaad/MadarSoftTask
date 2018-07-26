package com.example.ibrahimsaad.madarsofttask;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahimsaad.madarsofttask.database.WeatherEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private final List<WeatherEntity> weatherDataList;
    private final Context mContext;

    public WeatherRecyclerAdapter(List<WeatherEntity> weatherDataList, Context mContext) {
        this.weatherDataList = weatherDataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WeatherEntity weatherData = weatherDataList.get(position);
        holder.weatherDataTxtView.setText(weatherData.getText()); // for example

    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weatherDataTxtView)
        TextView weatherDataTxtView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

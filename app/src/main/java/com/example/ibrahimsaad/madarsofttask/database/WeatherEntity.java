package com.example.ibrahimsaad.madarsofttask.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather_data")
public class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;

    public WeatherEntity(String text) {
        this.id = id;
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}

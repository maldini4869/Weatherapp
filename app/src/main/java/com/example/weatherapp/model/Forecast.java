package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Forecast {
    @SerializedName("forecastday")
    private ArrayList<Forecastday> forecastdayArrayList;

    public ArrayList<Forecastday> getForecastdayArrayList() {
        return forecastdayArrayList;
    }

    public void setForecastdayArrayList(ArrayList<Forecastday> forecastdayArrayList) {
        this.forecastdayArrayList = forecastdayArrayList;
    }

}

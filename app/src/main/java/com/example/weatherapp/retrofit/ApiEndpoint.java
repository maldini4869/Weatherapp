package com.example.weatherapp.retrofit;

import com.example.weatherapp.model.Current;
import com.example.weatherapp.model.CurrentModel;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("current.json?key=51f23cd066f14f91af2124138212605&q=JAKARTA&aqi=yes")
    Call<CurrentModel> getCurrent();
    @GET("forecast.json?key=51f23cd066f14f91af2124138212605&q=Jakarta&days=3&aqi=no&alerts=no")
    Call<MainModel>getWeather();
}

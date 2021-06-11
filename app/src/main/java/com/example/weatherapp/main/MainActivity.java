package com.example.weatherapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.adapter.MainAdapter;
import com.example.weatherapp.model.Current;
import com.example.weatherapp.model.CurrentModel;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Forecastday;
import com.example.weatherapp.model.MainModel;
import com.example.weatherapp.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private final String TAG ="MainActivity";
    private RecyclerView rvCurrent;
    private MainAdapter mainAdapter;
    private ArrayList<Forecastday> forecastdayList =new ArrayList<>();
    private static MainActivity mainActivity;

    TextView wind_kph,pressure_mb, precip_mm, humidity, cloud, gust_kph, condition_text, temp_c, last_updated,location;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = findViewById(R.id.location);
        wind_kph = findViewById(R.id.wind_kph);
        pressure_mb = findViewById(R.id.pressure_mb);
        precip_mm = findViewById(R.id.precip_mm);
        humidity = findViewById(R.id.humidity);
        cloud = findViewById(R.id.cloud);
        gust_kph = findViewById(R.id.gust_kph);
        condition_text = findViewById(R.id.condition_text);
        temp_c = findViewById(R.id.temp_c);
        last_updated = findViewById(R.id.last_updated);
        icon = findViewById(R.id.icon);

        rvCurrent = findViewById(R.id.rv_current);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCurrent.setLayoutManager(layoutManager);
        mainActivity= this;

        getCurrentDatafromAPI();
        getForecastDatafromAPI();
    }
    private void getCurrentDatafromAPI(){
        ApiService.endpoint().getCurrent()
                .enqueue(new Callback<CurrentModel>() {
                    @Override
                    public void onResponse(Call<CurrentModel> call, Response<CurrentModel> response) {
                        if (response.isSuccessful()){
                            Log.d(TAG, "onResponse: berhasil");

                            CurrentModel current =response.body();
                            wind_kph.setText(Double.toString(current.getCurrent().getWind_kph())+" km/h");
                            pressure_mb.setText(Double.toString(current.getCurrent().getPressure_mb())+" mb");
                            precip_mm.setText(Double.toString(current.getCurrent().getPrecip_mm())+" mm");
                            humidity.setText(Integer.toString(current.getCurrent().getHumidity())+" %");
                            cloud.setText(Integer.toString(current.getCurrent().getCloud())+" %");
                            gust_kph.setText(Double.toString(current.getCurrent().getGust_kph())+" km/h");
                            condition_text.setText(current.getCurrent().getCondition().getText());
                            temp_c.setText(Double.toString(current.getCurrent().getTemp_c())+" °C");
                            last_updated.setText(current.getCurrent().getLast_updated());
                            location.setText(current.getLocation().getName());

                            Glide.with(MainActivity.this)
                                    .load("https:" + response.body().getCurrent().getCondition().getIcon() )
                                    .into(icon);


                        }

                    }

                    @Override
                    public void onFailure(Call<CurrentModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: gagal");
                        condition_text.setText(t.getMessage());
                    }
                });
    }
    public void getForecastDatafromAPI(){
        ApiService.endpoint().getWeather()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        if(response.isSuccessful()){

                            forecastdayList = response.body().getForecast().getForecastdayArrayList();
                            mainAdapter =new MainAdapter(forecastdayList);
                            rvCurrent.setAdapter(mainAdapter);

                            mainAdapter.setOnItemClickCallback(new MainAdapter.OnItemClickCallback() {
                                @Override
                                public void onItemClicked(Forecastday forecastday) {
                                    showSelectedDay(forecastday);
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
                        Log.d(TAG, "onFailure: hoho");
                    }
                });
    }
    private void showSelectedDay(Forecastday forecastday){
        Intent intent = new Intent (this, DetailActivity.class);
        intent.putExtra(DetailActivity.Extra_Forecast,forecastday);
        startActivity(intent);


    }
}
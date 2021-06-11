package com.example.weatherapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.adapter.DetailAdapter;
import com.example.weatherapp.model.Forecastday;
import com.example.weatherapp.model.Hour;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private final String TAG2 = "DetailActivity";
    private RecyclerView rvForecast;
    private DetailAdapter detailAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Hour> hourList = new ArrayList<>();

    public static final String Extra_Forecast ="Extra_Forecast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        rvForecast = findViewById(R.id.rv_forecast);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvForecast.setLayoutManager(layoutManager);

        Forecastday forecastday = getIntent().getParcelableExtra(Extra_Forecast);

        detailAdapter = new DetailAdapter(forecastday.getHour());
        rvForecast.setAdapter(detailAdapter);
    }
}
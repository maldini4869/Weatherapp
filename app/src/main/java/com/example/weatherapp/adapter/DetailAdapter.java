package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.model.Forecastday;
import com.example.weatherapp.model.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    List<Hour>HourList;

    public DetailAdapter (ArrayList<Hour> HourList){

        this.HourList =HourList;
    }

    @NonNull
    @Override
    public DetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_forecast,parent, false);
        DetailAdapter.DetailViewHolder detailViewHolder =new DetailAdapter.DetailViewHolder(view);
        return detailViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailViewHolder holder, int position) {

        String daytime = HourList.get(position).getTime();
        SimpleDateFormat fromAPI = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        SimpleDateFormat my = new SimpleDateFormat("HH:mm");
        String reformat=null;
        try{reformat= my.format(fromAPI.parse(daytime));
        }
        catch (ParseException a){
        a.printStackTrace();
        }
        holder.time.setText(reformat);
        holder.temp_c.setText(Double.toString(HourList.get(position).getTemp_c())+" °C");
        holder.pressure_mb.setText(Double.toString(HourList.get(position).getPressure_mb())+" mb");
        holder.wind_kph.setText(Double.toString(HourList.get(position).getWind_kph())+" Km/h");
        holder.precip_mm.setText(Double.toString(HourList.get(position).getPrecip_mm())+" mm");
        Glide.with(holder.itemView.getContext())
                .load("https:"+ HourList.get(position).getCondition().getIcon())
                .into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return HourList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        public TextView time,temp_c,pressure_mb,wind_kph,precip_mm;
        public ImageView icon;

        public DetailViewHolder (View itemview){
            super(itemview);
            time =itemview.findViewById(R.id.hour_time);
            temp_c= itemview.findViewById(R.id.hour_temp_c);
            pressure_mb = itemview.findViewById(R.id.hour_pressure_mb);
            wind_kph = itemview.findViewById(R.id.hour_wind_kph);
            precip_mm = itemview.findViewById(R.id.hour_precip_mm);
            icon = itemview.findViewById(R.id.hour_icon);
    }

}
}

package com.example.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.main.MainActivity;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Forecastday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private ArrayList<Forecastday> Daylist;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback =onItemClickCallback;
    }

    public MainAdapter(ArrayList<Forecastday>DayList){
        this.Daylist = DayList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_current,parent,false);
        return new MainViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        String datetime = Daylist.get(position).getDate();
        SimpleDateFormat fromAPI = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat my2 = new SimpleDateFormat("EEEE");
        String reformat2=null;
        try{reformat2= my2.format(fromAPI.parse(datetime));
        }
        catch (ParseException b){
            b.printStackTrace();
        }

    holder.date.setText(reformat2);
    holder.avgtemp_c.setText(Double.toString(Daylist.get(position).getDay().getAvgtemp_c())+" °C");
    holder.condition_text.setText((Daylist.get(position).getDay().getCondition().getText()));


        Glide.with(holder.itemView.getContext())
                .load("https:" + Daylist.get(position).getDay().getCondition().getIcon())
                .into(holder.icon);


    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClickCallback.onItemClicked(Daylist.get(holder.getAdapterPosition()));
        }
    });
    }

    @Override
    public int getItemCount() {
        return Daylist.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView date, condition_text, avgtemp_c;
        public ImageView icon;

        public MainViewHolder(View itemView){
            super(itemView);
            date= itemView.findViewById(R.id.date);
            condition_text= itemView.findViewById(R.id.condition_text);
            avgtemp_c= itemView.findViewById(R.id.avgtemp_c);
            icon=  itemView.findViewById(R.id.icon);
        }
    }
    public interface OnItemClickCallback{
        void onItemClicked (Forecastday forecastday);
;
    }
}
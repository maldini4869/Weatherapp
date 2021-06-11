package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Forecastday implements Parcelable {

    private ArrayList<Hour> hour;
    private String date;
    private Day day;

    protected Forecastday(Parcel in) {
        hour = in.createTypedArrayList(Hour.CREATOR);
        date = in.readString();
        day = in.readParcelable(Day.class.getClassLoader());
    }

    public static final Creator<Forecastday> CREATOR = new Creator<Forecastday>() {
        @Override
        public Forecastday createFromParcel(Parcel in) {
            return new Forecastday(in);
        }

        @Override
        public Forecastday[] newArray(int size) {
            return new Forecastday[size];
        }
    };

    public ArrayList<Hour> getHour() {
        return hour;
    }

    public void setHour(ArrayList<Hour> hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(hour);
        dest.writeString(date);
        dest.writeParcelable(day, flags);
    }
}

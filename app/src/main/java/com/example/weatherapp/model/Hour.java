package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hour implements Parcelable {

    private double precip_mm;
    private String time;
    private double temp_c;
    private double pressure_mb;
    private double wind_kph;
    private Condition condition;


    protected Hour(Parcel in) {
        precip_mm = in.readDouble();
        time = in.readString();
        temp_c = in.readDouble();
        pressure_mb = in.readDouble();
        wind_kph = in.readDouble();
        condition = in.readParcelable(Condition.class.getClassLoader());
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public double getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(double precip_mm) {
        this.precip_mm = precip_mm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(double pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(precip_mm);
        dest.writeString(time);
        dest.writeDouble(temp_c);
        dest.writeDouble(pressure_mb);
        dest.writeDouble(wind_kph);
        dest.writeParcelable(condition, flags);
    }
}
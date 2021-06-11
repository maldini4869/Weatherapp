package com.example.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Condition implements Parcelable {

    public String text;
    public String icon;
    public int code;

    protected Condition(Parcel in) {
        text = in.readString();
        icon = in.readString();
        code = in.readInt();
    }

    public static final Creator<Condition> CREATOR = new Creator<Condition>() {
        @Override
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        @Override
        public Condition[] newArray(int size) {
            return new Condition[size];
        }
    };

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(icon);
        dest.writeInt(code);
    }
}

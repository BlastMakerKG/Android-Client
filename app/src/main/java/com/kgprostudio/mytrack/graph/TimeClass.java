package com.kgprostudio.mytrack.graph;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class TimeClass implements Parcelable {

    protected TimeClass(Parcel in) {
        velocity = in.readDouble();
        ditsance = in.readDouble();
        hour = in.readInt();
        minute = in.readInt();
        second = in.readInt();
        time = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(velocity);
        dest.writeDouble(ditsance);
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(second);
        dest.writeLong(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TimeClass> CREATOR = new Creator<TimeClass>() {
        @Override
        public TimeClass createFromParcel(Parcel in) {
            return new TimeClass(in);
        }

        @Override
        public TimeClass[] newArray(int size) {
            return new TimeClass[size];
        }
    };

    public double getVelocity() {
        return velocity;
    }



    public TimeClass(double velocity, double ditsance, long time) {
        this.velocity = velocity;
        this.ditsance = ditsance;


       this.hour = (int)(time /3600000);
        this.minute = (int)(time - hour*3600000)/60000;
        this.second= (int)(time - hour*3600000- minute*60000)/1000 ;
        Log.d("time", second + "");
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getDitsance() {
        return ditsance;
    }

    public void setDitsance(double ditsance) {
        this.ditsance = ditsance;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    private double velocity;
    private double ditsance;
    private int hour, minute, second;
    private long time;
}

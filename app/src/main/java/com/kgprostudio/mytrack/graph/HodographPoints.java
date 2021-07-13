package com.kgprostudio.mytrack.graph;

import android.provider.ContactsContract;

import java.util.Date;

public class HodographPoints {

    public HodographPoints(double velocity, double distance, Date time) {
        this.velocity = velocity;
        this.distance = distance;
        this.time = time;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HodographPoints{" +
                "velocity=" + velocity +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }

    private double velocity;
    private double distance;
    private Date time;


}

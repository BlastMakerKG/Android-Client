package com.kgprostudio.mytrack.graph;

import java.io.Serializable;

public class DateClass implements Serializable {
    public double getDistance() {
        return distance;
    }

    public double getVelocity() {
        return velocity;
    }

    public float getTime() {
        return time;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private double distance;
    private double velocity;
    private long time;

    public DateClass(double distance, double velocity, long time) {
        this.distance = distance;
        this.velocity = velocity;
        this.time = time;
    }


}

package com.kgprostudio.mytrack.graph;

import java.io.Serializable;

public class DatePoint implements Serializable {

    public DatePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    float x,y;
}
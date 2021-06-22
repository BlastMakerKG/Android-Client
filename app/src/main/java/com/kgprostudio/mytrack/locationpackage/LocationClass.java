package com.kgprostudio.mytrack.locationpackage;

import java.io.Serializable;

public class LocationClass implements Serializable {

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * @return the alt
     */
    public double getAlt() {
        return alt;
    }

    /**
     * @param alt the alt to set
     */
    public void setAlt(double alt) {
        this.alt = alt;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    private int ID = 0;
    private String date;
    private double lat;
    private double lon;
    private double alt;
    private double distance;
    private double velocity;

    public LocationClass(int id, String date, double lat, double lon, double alt, double distance, double velocity) {
        this.ID = id;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.distance = distance;
        this.velocity = velocity;
    }

    public String ToString() {
        return ID + "," + date + "," + lat + "," + lon + "," + alt + "," + distance + "," + velocity;
    }

}
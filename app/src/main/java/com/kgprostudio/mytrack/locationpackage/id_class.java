package com.kgprostudio.mytrack.locationpackage;

public class id_class {

    public id_class() {
    }

    public id_class(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public LocationClass getLocation() {
        return location;
    }

    public void setLocation(LocationClass location) {
        this.location = location;
    }

    LocationClass location;
}

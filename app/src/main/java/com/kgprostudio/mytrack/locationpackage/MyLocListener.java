package com.kgprostudio.mytrack.locationpackage;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MyLocListener implements LocationListener, Serializable {

    public void setLocListenerInterface(LocListenerInterface locListenerInterface) {
        this.locListenerInterface = locListenerInterface;
    }

    private LocListenerInterface locListenerInterface;
    @Override
    public void onLocationChanged(@NonNull Location location) {
        locListenerInterface.OnLocationChanged(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}

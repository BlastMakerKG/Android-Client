package com.kgprostudio.mytrack.map_tools;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kgprostudio.mytrack.MainActivity;
import com.kgprostudio.mytrack.R;

public class MapTools extends MainActivity {
    static boolean flag = true;
    static PolylineOptions line =  new PolylineOptions();
    static  Marker markerName;
    static MarkerOptions markerOptions;
    static int counter = 0;

    // Рисование линии маршрута
    public static void DrawLine(GoogleMap googleMap, LatLng latLng) {

        if (flag) {
            Log.d("FirstInit", "FisrtInitDone");
            setCameraGmap(googleMap,latLng);
            flag = false;
        }

        else {
           // googleMap.addMarker(new MarkerOptions().position(latLng).title("Start location")).setIcon(BitmapDescriptorFactory.defaultMarker(250));
            if (null != markerName) { markerName.remove(); }
            markerName = googleMap.addMarker(new MarkerOptions().position(latLng).title("Title"));
        }


      //  if (null != currentLocationMarker) { currentLocationMarker.remove(); }

        line.add(latLng).width(20).color(R.color.track_color);
        Log.d("Add point", counter++ +"");

        googleMap.addPolyline(line);
        Log.d("Map draw",  counter + "");
    }

    // Приведение камеры в текущие координаты
    public static void setCameraGmap(GoogleMap googleMap,LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);
    }

}

package com.kgprostudio.mytrack.map_tools;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kgprostudio.mytrack.MapsActivity;
import com.kgprostudio.mytrack.R;

public class MapTools extends MapsActivity {
    static boolean flag = true;
    static PolylineOptions line =  new PolylineOptions();
    static  Marker markerName;
    static MarkerOptions markerOptions;
    static int counter = 0;

    public static void DrawLine(GoogleMap googleMap, LatLng latLng) {

        if (flag) {
           // line = new PolylineOptions().add(latLng).width(20).color(R.color.track_color);

            //new PolylineOptions().add(new LatLng(42.872797, 74.587889),
            //        new LatLng(42.875313, 74.588243),
            //        new LatLng(42.875124, 74.591503),
            //        new LatLng(42.873894, 74.591417),
            //        new LatLng(42.873792, 74.593348))

            flag = false;
            Log.d("FirstInit", "FisrtInitDone");
            setCameraGmap(googleMap,latLng);
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

    public static void setCameraGmap(GoogleMap googleMap,LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(15)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);
    }

    public  static void putMarkerInMap(GoogleMap googleMap, LatLng latLng, String tittle)
    {

    }
}

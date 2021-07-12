package com.kgprostudio.mytrack;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.kgprostudio.mytrack.connectingtoserver.Client;
import com.kgprostudio.mytrack.file_system.XMLWorker;
import com.kgprostudio.mytrack.locationpackage.LocationClass;
import com.kgprostudio.mytrack.map_tools.MapTools;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kgprostudio.mytrack.dbhelper.DBHelper;
import com.kgprostudio.mytrack.locationpackage.LocListenerInterface;
import com.kgprostudio.mytrack.locationpackage.MyLocListener;
import com.kgprostudio.mytrack.test.TestClass;

import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, LocListenerInterface {

    private LocationManager locManager;
    private Location lastLocation;
    private Location NowLocation;
    private MyLocListener myLocListener;
    private float distanc = 0;
    private double speed = 0;
    private TextView lat_tv, lon_tv, alt_tv, distance_tv, velocity_tv, status_msg;
    private LatLng currentLanLng;
    private GoogleMap mMap;
    private PolylineOptions line;
    private Socket socket;
    private LocationClass loc;

    private Chronometer timer_track;
    private Chronometer timer_track1;

    private DBHelper dbHelper;

    private ArrayList<Location> locationArrayList = new ArrayList<Location>();
    private ArrayList<Date> dateArrayList = new ArrayList<Date>();

    private int CurrentIndex = 0;
    private final String TAG = "LifeCycle";
    private TextView coun_Text;
    private Button count_btn;
    private Date date_now = new Date();
    private int count;
    private Button record_btn, stop_btn, pause_btn, draw_btn, currLoc_btn, pause_rec_btn;
    private boolean flag = true, flag_convert = true;

    private boolean clienCon = true;
    private double LatLngKoef = 0.0;

    private double lastdistance = 0;
    private double latit = 0;
    private double longit = 0;
    private double altid = 0;

    private Date dateNow;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private String date_format = "";
    private LocationClass locClass;
    MainActivity prevActivity;

    private int idCount = 1;

    private long lastTime = 0;
    private Bundle savedInstanceState;
    private double temp = 0;

    private String ip;
    private int port;

    public static final String obj_key = "First_Object";
    public static final String LatLng_key = "coordinates_key";
    public static final String map_key = "map_key";
    private ArrayList<LatLng> latLngs;
    TestClass testClass;
    Client[] clients = new Client[4];

    LocationClass[] locationClass = new LocationClass[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //savedInstanceState = new Bundle();
        //prevActivity = (MapsActivity)getLastNonConfigurationInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState != null) {
            // mMap = savedInstanceState.getParcelable(map_key);
            latLngs = savedInstanceState.getParcelableArrayList(LatLng_key);
            for (int i = 0; i < latLngs.size(); i++) {
                if (mMap != null) {
                    MapTools.DrawLine(mMap, latLngs.get(i));
                }
                System.out.println(latLngs.get(i));
            }
        } else {
            latLngs = new ArrayList<LatLng>();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_id);
        mapFragment.getMapAsync(this);

        InitLocation();
        InitNav();
        btnClickListener();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList(LatLng_key, latLngs);
        //avedInstanceState.putParcelable(map_key, (Parcelable) mMap);
        super.onSaveInstanceState(savedInstanceState);
    }

    private GoogleMap getmMap() {
        return mMap;
    }

    private void InitLocation() {
        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocListener = new MyLocListener();
        myLocListener.setLocListenerInterface(this);
        CheckPermission();
        dbHelper = new DBHelper(this);
        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            ip = arguments.get("ip").toString();
            port = arguments.getInt("port");

        }
    }

    public void btnClickListener() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        testClass = new TestClass(10, "Erlan");

        record_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clienCon && ip!= null && port != 0) {
                    LatLng startLoc = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(startLoc).title("Start location")).setIcon(BitmapDescriptorFactory.defaultMarker(250));


                    for(int i = 0; i < 4; i++)
                    {
                        clients[i] = new Client();
                        clients[i].ConnectToServer(ip, port);
                    }

                    // timer_track.setBase(SystemClock.elapsedRealtime());
                }
                timer_track.setBase(SystemClock.elapsedRealtime());
                if (lastTime != 0) {
                    timer_track.setBase(timer_track.getBase() + SystemClock.elapsedRealtime() - lastTime);
                }

                timer_track.start();
                if (lastLocation == null) {
                    Toast.makeText(MainActivity.this, "Требуется определение местоположения ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Запись данных  началась...", Toast.LENGTH_SHORT).show();
                    record_btn.setEnabled(false);
                    record_btn.setVisibility(View.GONE);
                    stop_btn.setEnabled(true);
                    stop_btn.setVisibility(View.VISIBLE);
                    pause_btn.setEnabled(true);
                    pause_btn.setVisibility(View.VISIBLE);

                    Record(lastTime);
                }
            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer_track.stop();
                timer_track.setBase(SystemClock.elapsedRealtime());
                lastTime = 0;
                record_btn.setVisibility(View.VISIBLE);
                record_btn.setEnabled(true);

                stop_btn.setEnabled(false);
                pause_btn.setEnabled(false);
                pause_rec_btn.setEnabled(false);

                pause_rec_btn.setVisibility(View.GONE);
                stop_btn.setVisibility(View.GONE);
                pause_btn.setVisibility(View.GONE);


                LatLng finishLoc = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                MapTools.putMarkerInMap(mMap, finishLoc, "Track started location");

                Toast.makeText(MainActivity.this, "Запись данных окончена и сохранена", Toast.LENGTH_SHORT).show();
                record_btn.setEnabled(true);
                stop_btn.setEnabled(false);
            }
        });

        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause_btn.setEnabled(false);
                pause_btn.setVisibility(View.GONE);

                pause_rec_btn.setEnabled(true);
                pause_rec_btn.setVisibility(View.VISIBLE);

                lastTime = SystemClock.elapsedRealtime();
                timer_track.stop();
            }
        });

        pause_rec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                XMLWorker file_worker = new XMLWorker();
                file_worker.writeFile(locationClass[1]);
                pause_rec_btn.setEnabled(false);
                pause_rec_btn.setVisibility(View.GONE);

                pause_btn.setEnabled(true);
                pause_btn.setVisibility(View.VISIBLE);

                Record(lastTime);

            }
        });

        currLoc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapTools.setCameraGmap(mMap, currentLanLng);
                // DateBaseTools.ShowDB(database, dbHelper);

                for (int i = 0; i < latLngs.size(); i++) {
                    System.out.println(latLngs.get(i));
                }
            }
        });
    }

    public void Record(long last_time) {


        if (last_time != 0) {
            timer_track.setBase(timer_track.getBase() + SystemClock.elapsedRealtime() - last_time);
        }
        timer_track.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                double count= 0.1;
                while (pause_btn.isEnabled()) {
                    date_now = new Date();
                    date_format = dateFormat.format(date_now);
                    locationClass[0] = new LocationClass(1, date_format, lastLocation.getLatitude()-count, lastLocation.getLongitude(), lastLocation.getAltitude(), distanc, speed);
                    locationClass[1] = new LocationClass(2, date_format, lastLocation.getLatitude()+count, lastLocation.getLongitude(), lastLocation.getAltitude(), distanc, speed);
                    locationClass[2] = new LocationClass(3, date_format, lastLocation.getLatitude(), lastLocation.getLongitude()+count, lastLocation.getAltitude(), distanc, speed);
                    locationClass[3] = new LocationClass(4, date_format, lastLocation.getLatitude(), lastLocation.getLongitude()-count, lastLocation.getAltitude(), distanc, speed);


                    count += 0.1;

                    //  DateBaseTools.RecordToDB(database, contentValues, locClass, stop_btn);

                    try {
                        for (int i=0; i<4; i++)
                        {
                            clients[i].TransferToServer(locationClass[i],i);
                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    if (currentLanLng != null) {
                        temp = temp + 0.001;
                        currentLanLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude() + temp);
                        latLngs.add(currentLanLng);
                        Log.d("Coordinates", currentLanLng.toString());
                        // temp += 0.001;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MapTools.DrawLine(mMap, currentLanLng);
                                Log.d("MainThread", currentLanLng.toString());
                            }
                        });

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }

    public void InitNav() {
        timer_track = findViewById(R.id.timer_track);
        status_msg = findViewById(R.id.status_msg);
        // draw_btn = findViewById(R.id.draw_track_btn);
        distance_tv = findViewById(R.id.distance_text);
        velocity_tv = findViewById(R.id.velocity_text);

        record_btn = findViewById(R.id.record_btn);
        record_btn.setEnabled(true);
        record_btn.setBackgroundResource(R.drawable.record_png_btn);

        stop_btn = findViewById(R.id.stop_btn);
        stop_btn.setEnabled(false);
        stop_btn.setBackgroundResource(R.drawable.stop_png_btn);
        stop_btn.setVisibility(View.GONE);

        pause_btn = findViewById(R.id.pause_btn);
        pause_btn.setBackgroundResource(R.drawable.pause_png_btn);
        pause_btn.setVisibility(View.GONE);
        pause_btn.setEnabled(false);

        pause_rec_btn = findViewById(R.id.pause_rec_btn);
        pause_rec_btn.setBackgroundResource(R.drawable.record_png_btn);
        pause_rec_btn.setEnabled(false);
        pause_rec_btn.setVisibility(View.GONE);

        currLoc_btn = findViewById(R.id.current_loc_btn);
        currLoc_btn.setBackgroundResource(R.drawable.location_icon);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setSelectedItemId(R.id.map_nav_btn);
        lon_tv = findViewById(R.id.long_text);
        lat_tv = findViewById(R.id.lat_text);
        alt_tv = findViewById(R.id.alt_text);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.my_tracks_nav_btn:
                        startActivity(new Intent(getApplicationContext(), MyTracksActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.map_nav_btn:
                        return true;
                    case R.id.server_con_nav_btn:
                        startActivity(new Intent(getApplicationContext(), TruckPointActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about_nav_btn:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (prevActivity != null) {
            // So the orientation did change
            // Restore some field for example
            this.mMap = prevActivity.mMap;
        } else {
            mMap = googleMap;
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

            if (lastLocation != null) {
                LatLng currentLoc = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

                // mMap.addMarker(new MarkerOptions().position(currentLoc).title("Marker in Sydney"));

                MapTools.putMarkerInMap(mMap, currentLoc, "Track started location");
                LatLng ltnLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                // mMap.moveCamera(CameraUpdateFactory.newLatLng(bishkek));
                MapTools.setCameraGmap(mMap, ltnLng);
            } else {
                LatLng currentLoc = new LatLng(42.873686, 74.614307);

                mMap.addMarker(new MarkerOptions().position(currentLoc).title("Marker in Sydney"));

                // mMap.moveCamera(CameraUpdateFactory.newLatLng(bishkek));
                MapTools.setCameraGmap(mMap, currentLoc);
            }

            try {
                // Customise the styling of the base map using a JSON object defined
                // in a raw resource file.
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                this, R.raw.style_json));
                if (!success) {
                    Log.e(TAG, "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e(TAG, "Can't find style. Error: ", e);
            }
        }
    }

    @Override
    public void OnLocationChanged(Location loc) {

        lat_tv.setText(String.format("Широта: %4.6f", loc.getLatitude()));
        lon_tv.setText(String.format("Долгота: %4.6f", loc.getLongitude()));
        alt_tv.setText(String.format("Высота: %.2f", loc.getAltitude()));

        if (loc.hasSpeed() && lastLocation != null) {
            distanc = (distanc + lastLocation.distanceTo(loc));
        }

        lastLocation = loc;
        distance_tv.setText(String.format("%.1f", distanc) + " м");
        Log.d("Distance", distanc + "");
        // velocity.setText(String.format("%.2f км/ч",String.valueOf(loc.getSpeed()*3.6)));
        velocity_tv.setText(String.format("%.2f км/ч", loc.getSpeed() * 3.6));
        currentLanLng = new LatLng(loc.getLatitude(), loc.getLongitude());
    }

    private void CheckPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, myLocListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == RESULT_OK) {
            CheckPermission();
        } else {
            Toast.makeText(this, "NO GPS permissions ", Toast.LENGTH_SHORT).show();
        }
    }


}

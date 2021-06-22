package com.kgprostudio.mytrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyTracksActivity extends AppCompatActivity {

    private final String TAG = "LifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tracks);
        InitNav();
    }

    public  void InitNav()
    {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setSelectedItemId(R.id.my_tracks_nav_btn);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId())
                {
                    case R.id.my_tracks_nav_btn:
                        return true;
                    case R.id.map_nav_btn:
                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.server_con_nav_btn:
                        startActivity(new Intent(getApplicationContext(), ConnectServerActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about_nav_btn:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MyTracksActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MyTracksActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MyTracksActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MyTracksActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MyTracksActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyTracksActivity onDestroy");
    }
}
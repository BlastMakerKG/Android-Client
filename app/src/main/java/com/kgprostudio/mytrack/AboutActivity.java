package com.kgprostudio.mytrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        InitNav();
    }

    public  void InitNav() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setSelectedItemId(R.id.about_nav_btn);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.my_tracks_nav_btn:
                        startActivity(new Intent(getApplicationContext(), MyTracksActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.map_nav_btn:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.server_con_nav_btn:
                        startActivity(new Intent(getApplicationContext(), TruckPointActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about_nav_btn:
                        return true;
                }
                return false;
            }
        });
    }
}
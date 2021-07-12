package com.kgprostudio.mytrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kgprostudio.mytrack.opengl.OpenGLRenderer;

public class MyTracksActivity extends AppCompatActivity {

    private final String TAG = "LifeCycle";
    private GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!supportES2()) {
            Toast.makeText(this, "OpenGl ES 2.0 is not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new OpenGLRenderer());
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    private boolean supportES2() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        return (configurationInfo.reqGlEsVersion >= 0x20000);
    }

   // public  void InitNav()
   // {
   //     BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
   //     bottomNavigationView.setSelectedItemId(R.id.my_tracks_nav_btn);
//
   //     bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
   //         @Override
   //         public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
   //             switch (menuitem.getItemId())
   //             {
   //                 case R.id.my_tracks_nav_btn:
   //                     return true;
   //                 case R.id.map_nav_btn:
   //                     startActivity(new Intent(getApplicationContext(), MainActivity.class));
   //                     overridePendingTransition(0,0);
   //                     return true;
   //                 case R.id.server_con_nav_btn:
   //                     startActivity(new Intent(getApplicationContext(), LoginActivity.class));
   //                     overridePendingTransition(0,0);
   //                     return true;
   //                 case R.id.about_nav_btn:
   //                     startActivity(new Intent(getApplicationContext(), AboutActivity.class));
   //                     overridePendingTransition(0,0);
   //                     return true;
   //             }
   //             return false;
   //         }
   //     });
   // }
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
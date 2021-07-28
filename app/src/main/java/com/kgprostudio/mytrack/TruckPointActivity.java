package com.kgprostudio.mytrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.kgprostudio.mytrack.graph.DrawView;
import com.kgprostudio.mytrack.graph.TimeClass;


import java.util.ArrayList;

public class TruckPointActivity extends AppCompatActivity {

    private final static String TAG = "TruckPointActivity";
    DrawView drawView;

    Button draw;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;
    ArrayList<Integer> test = new ArrayList<>();

    ArrayList<TimeClass> timeClassArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_truck_point);
      init();
    }

    public void init(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setSelectedItemId(R.id.truck_point_btn);

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
                    case R.id.truck_point_btn:
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

    public void onClick(View view){



        try {
            Runnable runnable = new Runnable() {
                int count =0, test_value =0;
                @Override
                public void run() {
                    GraphView graph = (GraphView) findViewById(R.id.graph1);
                    series = new LineGraphSeries<DataPoint>();
                    series.setColor(Color.BLUE);
                    series1 = new LineGraphSeries<DataPoint>();
                    series1.setColor(Color.RED);

                    while(true){


                        Intent myObj = getIntent();
                        TimeClass time =  (TimeClass) myObj.getParcelableExtra("time_date");
                        test_value =  myObj.getIntExtra("test_int",0);
                        Log.d("Count" , timeClassArrayList.size() +" размер");
                        timeClassArrayList.add(time);
                        try {
                            Thread.sleep(1000);
                            graph.removeAllSeries();
                            graph.addSeries(series);
                            graph.addSeries(series1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("Count" , timeClassArrayList.size() +" размер");
                        System.out.println(timeClassArrayList.size() + " размер");
                        series.appendData(new DataPoint(count, count), false, 20);
                        series1.appendData(new DataPoint( count,count+(Math.random()*2)+count), false, 20);
                        count++;
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    //        Runnable run = new Runnable() {
    //            @Override
    //            public void run() {
    //                double y,x;
    //                x = -5.0;
    //                for (int i=0; i <10;i++){
    //                GraphView graph = (GraphView) findViewById(R.id.graph1);
    //
    //                series1 = new LineGraphSeries<DataPoint>();
    //                for(int j =0; j<10; j++) {
    //                    x = x + 0.1;
    //                    y = Math.sin(x);
    //                    series.appendData(new DataPoint(x, y), true, 100);
    //                    series1.appendData(new DataPoint(x+1, y+1), true, 100);
    //                }
    //                runOnUiThread(new Runnable() {
    //                    @Override
    //                    public void run() {
    //                        graph.addSeries(series);
    //                        graph.addSeries(series1);
    //                    }
    //                });
//
    //                    try {
    //                        Thread.sleep(2000);
    //                    } catch (InterruptedException e) {
    //                        e.printStackTrace();
    //                    }
    //                }
    //        }
    //    };
    //    Thread thread = new Thread(run);
    //    thread.start();


    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }



}
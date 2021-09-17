package com.kgprostudio.mytrack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.series.DataPoint;
import com.kgprostudio.mytrack.graph.DatePoint;
import com.kgprostudio.mytrack.notinterested.DemoBase;

import java.util.ArrayList;


public class TruckPointActivity extends DemoBase implements
        OnChartValueSelectedListener {

    private LineChart chart;
    Switch sw;
    private int lenght_track = 200, amount =0, temp;
    private ProgressBar progressBar;

    private float fact_vel =0, optim_vel=0;

    private float pred =0,now=0;

  private ArrayList<DataPoint> myDate = new ArrayList<DataPoint>();

  DatePoint[] datePoints = new DatePoint[]{
          new DatePoint(0,0),
          new DatePoint(10, 0.10f),
          new DatePoint(20, 0.17f),
          new DatePoint(30, 0.24f),
          new DatePoint(40, 0.32f),
          new DatePoint(50, 0.36f),
          new DatePoint(60, 0.41f),
          new DatePoint(70, 0.47f),
          new DatePoint(80, 0.50f),
          new DatePoint(90, 0.53f),
          new DatePoint(100, 0.57f),
          new DatePoint(110, 0.64f),
          new DatePoint(120, 0.72f),
          new DatePoint(130, 0.80f),
          new DatePoint(140, 0.87f),
          new DatePoint(150, 0.94f),
          new DatePoint(160, 1.00f),
          new DatePoint(170, 1.05f),
          new DatePoint(180, 1.11f),
          new DatePoint(190, 1.15f),
          new DatePoint(200, 1.18f),
          new DatePoint(210, 1.23f),
          new DatePoint(220, 1.28f),
          new DatePoint(230, 1.34f),
          new DatePoint(240, 1.39f),
          new DatePoint(250, 1.47f)

  };

    DatePoint[] datePoints1 = new DatePoint[]{
            new DatePoint(0,0),
            new DatePoint(10, 0.05f),
            new DatePoint(20, 0.12f),
            new DatePoint(30, 0.15f),
            new DatePoint(40, 0.20f),
            new DatePoint(50, 0.28f),
            new DatePoint(60, 0.35f),
            new DatePoint(70, 0.40f),
            new DatePoint(80, 0.45f),
            new DatePoint(90, 0.53f),
            new DatePoint(100, 0.57f),
            new DatePoint(110, 0.62f),
            new DatePoint(120, 0.70f),
            new DatePoint(130, 0.78f),
            new DatePoint(140, 0.85f),
            new DatePoint(150, 0.92f),
            new DatePoint(160, 1.00f),
            new DatePoint(170, 1.05f),
            new DatePoint(180, 1.11f),
            new DatePoint(190, 1.15f),
            new DatePoint(200, 1.18f),
            new DatePoint(210, 1.23f),
            new DatePoint(220, 1.28f),
            new DatePoint(230, 1.34f),
            new DatePoint(240, 1.39f),
            new DatePoint(250, 1.47f)

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_truck_point);

        setTitle("RealtimeLineChartActivity");
        init();

        chart = findViewById(R.id.realtime_chart);
        chart.setOnChartValueSelectedListener(this);

        // enable description text
        chart.getDescription().setEnabled(true);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(true);
        chart.setDrawBorders(true);

        // set an alternative background color
        chart.setBackgroundColor(Color.WHITE);

        LineData data = new LineData();

        data.setValueTextColor(Color.BLACK);

        // add empty data
        chart.setData(data);



        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(LegendForm.LINE);
        l.setTypeface(tfLight);
        l.setTextColor(Color.WHITE);

        XAxis xl = chart.getXAxis();

        xl.setTypeface(tfLight);
        xl.setTextColor(Color.BLACK);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.BLACK);

        //leftAxis.setDrawGridLines(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(true);
    }

    private void addEntry() {

        LineData data = chart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            ILineDataSet set1 = data.getDataSetByIndex(1);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet(Color.rgb(51, 181, 229), "Фактическая скорость");
                set1 = createSet(Color.rgb(251, 181, 59),"Оптимальная скорость");
                data.addDataSet(set);
                data.addDataSet(set1);
                data.addEntry(new Entry(0, (float) fact_vel), 0);
                data.addEntry(new Entry(0, (float) optim_vel), 1);
            }

            pred = fact_vel;
            //fact_vel =(float)(set.getEntryCount()+Math.random()*20);
           // optim_vel = (float)(set1.getEntryCount() + 10f);

            Intent myObj = getIntent();


           // Log.d("Count" , time.toString());

          // if(fact_vel < pred){
          //  while (fact_vel < pred+5){
          //      fact_vel =(float)(set.getEntryCount()+Math.random()*20);
          //  }
          // }

            try {
                data.addEntry(new Entry(datePoints[set.getEntryCount()].getX(), (datePoints[set.getEntryCount()].getY())), 0);
                data.addEntry(new Entry(datePoints1[set.getEntryCount()].getX(), (datePoints1[set.getEntryCount()].getY())), 1);
            }

            catch (Exception exception){

            }


            amount = set.getEntryCount();
            data.notifyDataChanged();

            // let the chart know it's data has changed
            chart.notifyDataSetChanged();

            // limit the number of visible entries
            chart.setVisibleXRangeMaximum(100);
           // chart.setVisibleYRange(50,100,set.getAxisDependency());

            // chart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart.moveViewToX(data.getEntryCount());

            // this automatically refreshes the chart (calls invalidate())
            // chart.moveViewTo(data.getXValCount()-7, 55f,
            // AxisDependency.LEFT);
        }
    }


    public void init(){

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_menu);
        bottomNavigationView.setSelectedItemId(R.id.truck_point_btn);

        progressBar = findViewById(R.id.progressBar3);
        progressBar.setMax(lenght_track);
        sw = (Switch) findViewById(R.id.switch_start_stop);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    feedMultiple();
                } else {

                }
            }


        });

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
    private LineDataSet createSet(int color, String desc) {

        LineDataSet set = new LineDataSet(null, desc);
        set.setAxisDependency(AxisDependency.RIGHT);
        //set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //set.setCubicIntensity(0.2f);
        set.setColor(color);
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(3f);
        set.setCircleRadius(0.5f);
        set.setFillAlpha(65);
        set.setFillColor(color);
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        set.setDrawIcons(true);
        set.setDrawCircles(false);
        return set;
    }

    private Thread thread;

    private void feedMultiple() {

        if (thread != null)
            thread.interrupt();

        final Runnable runnable = new Runnable() {

            @Override
            public void run() {

                addEntry();
            }
        };

        thread = new Thread(new Runnable() {


            @Override
            public void run() {
                 while (sw.isChecked()){

                    // Don't generate garbage runnables inside the loop.
                    runOnUiThread(runnable);

                    try {
                        if(fact_vel > optim_vel*1.1){
                            Log.d("velocity", "уменьшите скосроть");
                        }
                        if(fact_vel < optim_vel*0.9){
                            Log.d("velocity","увеличте скосроть");

                        }
                        progressBar.setProgress(amount);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.realtime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/RealtimeLineChartActivity.java"));
                startActivity(i);
                break;
            }
            case R.id.actionAdd: {
                addEntry();
                break;
            }
            case R.id.actionClear: {
                chart.clearValues();
                Toast.makeText(this, "Chart cleared!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.actionFeedMultiple: {
                feedMultiple();
                break;
            }
            case R.id.actionSave: {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    requestStoragePermission(chart);
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "RealtimeLineChartActivity");
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (thread != null) {
            thread.interrupt();
        }
    }
}
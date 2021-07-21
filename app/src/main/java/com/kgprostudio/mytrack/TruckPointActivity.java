package com.kgprostudio.mytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.kgprostudio.mytrack.graph.DrawView;
import com.kgprostudio.mytrack.graph.TimeClass;


import org.apache.commons.collections.ArrayStack;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class TruckPointActivity extends AppCompatActivity {
    DrawView drawView;

    Button draw;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;

    ArrayList<TimeClass> timeClassArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_truck_point);
    }

    public void onClick(View view){

        GraphView graph = (GraphView) findViewById(R.id.graph1);
        series = new LineGraphSeries<DataPoint>();
        series.setColor(Color.BLUE);
        series1 = new LineGraphSeries<DataPoint>();
        series1.setColor(Color.RED);

        try {
            Runnable runnable = new Runnable() {
                int count =0;
                @Override
                public void run() {

                    while(true){

                        Intent myObj = getIntent();
                        TimeClass time =  (TimeClass) myObj.getParcelableExtra("time_date");
                        Log.d("Count" , timeClassArrayList.size() +" размер");
                        timeClassArrayList.add(time);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d("Count" , timeClassArrayList.size() +" размер");
                        System.out.println(timeClassArrayList.size() + " размер");
                        series.appendData(new DataPoint(count, count), false, 1000);
                        series1.appendData(new DataPoint( count,count+(Math.random()*2)+count), false, 1000);
                        count++;
                        runOnUiThread(new Runnable() {
                                          @Override
                                            public void run() {
                                              graph.removeAllSeries();
                                              graph.addSeries(series);
                                              graph.addSeries(series1);
                                          }
                              });

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



}
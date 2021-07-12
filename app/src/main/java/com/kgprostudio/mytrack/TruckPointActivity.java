package com.kgprostudio.mytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.kgprostudio.mytrack.graph.DrawView;


import static java.security.AccessController.getContext;

public class TruckPointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_point);
        LinearLayout linearLayout = findViewById(R.id.li);
        DrawView worker = new DrawView(this);
        linearLayout.addView(worker);


        // GraphView graph = (GraphView) findViewById(R.id.graph);


        // int[] Data= {10,46,53,58,63,67,69,72,75,78,82,85,
        //         90,95,99,105,110,500,121,126,132,137,143,
        //         148,153,157,162,165,168,170,173,174,175,176,
        //         177,177,177,176,176,175,173,172,171,169,168,
        //         167,166,164,161,155,147,136,123,111,101,92,84,
        //         78,74,70,67,65,64,62,61,61,60,60,59,59,58,58,
        //         58,57,57,57,56,56,56,56,56,56,56,55,55,55,55,
        //         55,54,54,500,500,500,};


        // LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        // series.setColor(Color.rgb(0,80,100)); //встановити колір кривої
        // series.setDrawDataPoints(false); // промальовувати точки
        // series.setDataPointsRadius(10); // радіус точки даних
        // series.setThickness(4); //товщина лінії

        //     Runnable run = new Runnable() {
        //         @Override
        //         public void run() {
        //             for (int i=0;i<93;i++) {
        //             series.appendData(new DataPoint(i,Data[i]),true,1000);
        //             runOnUiThread(new Runnable() {
        //                 @Override
        //                 public void run() {
        //                     graph.addSeries(series);
        //                 }
        //             });

        //                 try {
        //                     Thread.sleep(2000);
        //                 } catch (InterruptedException e) {
        //                     e.printStackTrace();
        //                 }
        //             }
        //     }

        // };
        //     Thread thread = new Thread(run);
        //     thread.start();
        // //Задаємо зовнішній вигляд кривої


        // //Назва графіка
        // graph.setTitle("Expenses");
        // graph.setTitleTextSize(50);
        // graph.setTitleColor(Color.RED);
        // //Легенда


        // //Підписи осей
        // GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        // gridLabel.setHorizontalAxisTitle("X Axis Title");
        // gridLabel.setVerticalAxisTitle("Y Axis Title");
        //
    }



}
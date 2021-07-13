package com.kgprostudio.mytrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
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


import static java.security.AccessController.getContext;

public class TruckPointActivity extends AppCompatActivity {
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_truck_point);
        init();


    }

    public void init(){
        drawView = findViewById(R.id.draw_view);
        Button click = findViewById(R.id.draw_btn);
    }

    public void onClick(View view){
        try {
            drawView.setDrawingStatus("draw");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }



}
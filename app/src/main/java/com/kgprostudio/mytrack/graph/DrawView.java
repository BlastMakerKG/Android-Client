package com.kgprostudio.mytrack.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class DrawView extends View {

    Paint p;
    Rect rect;

    public DrawView(Context context) {
        super(context);
        p = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // заливка канвы цветом
        canvas.drawARGB(80, 255,255,255);

        // настройка кисти
        // красный цвет
        p.setColor(Color.BLACK);
        // толщина линии = 10
        p.setStrokeWidth(10);


        // рисуем линию от (100,100) до (500,50)
        canvas.drawLine(50,50,50,1000,p);
        canvas.drawLine(50,50,500,50,p);


    }

}

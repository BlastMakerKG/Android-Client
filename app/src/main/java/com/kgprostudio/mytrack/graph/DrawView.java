package com.kgprostudio.mytrack.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawView extends View {

    Paint p;
    Rect rect;
    String drawingStatus;
    private RectF mBounds = new RectF(-850, 50, 850, 1700);
    int dx =1;
    boolean firstInit = true;
    final int radius = 5;
    int x0 = -850, y0 = -850;
    int x1 = 850, y1 = 850;
    final RectF oval = new RectF(-850, 50, 850, 1650);

    public DrawView(final Context context,AttributeSet attributeSet) {
        super(context);
        setWillNotDraw(false);
        p = new Paint();
        rect = new Rect();
        p.setAntiAlias(true);
        Log.d("oBJ", "оБЬЕКТ СОЗДАН");
    }

    public void setDrawingStatus(String drawingStatus) {
        this.drawingStatus = drawingStatus;
        invalidate();
    }


    @Override
    protected void onSizeChanged(final int width, final int height, final int oldw,
                                 final int oldh) {
        mBounds.left = 0;
        mBounds.top = 0;
        mBounds.right = width;
        mBounds.bottom = height;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // заливка канвы цветом
            if(dx >700)
                dx=1;
            canvas.drawARGB(80, 255, 255, 255);
            p.setColor(Color.BLACK);
            p.setStrokeWidth(15);
            canvas.drawLine(50, 50, 50, 851, p);
            canvas.drawLine(49, 850, 850, 850, p);
            p.setStyle(Paint.Style.STROKE);

            p.setColor(Color.RED);

            canvas.drawArc(oval,-90,90,false,p);

            p.setColor(Color.RED);
            p.setStrokeWidth(10);
            canvas.drawLine(50+dx,850,850,50,p);

            p.setColor(Color.GREEN);
            p.setStrokeWidth(10);
            canvas.drawLine(50,850-dx,850,50,p);
            dx=dx+2;
            Log.d("draw","просисовано");

            invalidate();
    }

}

package com.lukas_tamz.braintrainer.models;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by ldockal on 11/24/2017.
 */

public class GridCell extends Shape {

    private boolean clicked;
    private int correctColor = Color.GREEN;
    private int incoectColor = Color.RED;
    private int firstStateColor = Color.argb(255, 26, 127, 146);


    public GridCell(int id, PointF position, int width, Paint paint, ShapeOffset shapeOffset, BitmapParam bitmapParam) {
        super(id, position, width, width, paint, shapeOffset, bitmapParam);
        this.clicked = false;
    }

    public GridCell(int id, PointF position, int width, Paint paint, ShapeOffset shapeOffset) {
        this(id, position, width, paint, shapeOffset, null);
    }

    public GridCell(int id, PointF position, int width, Paint paint) {
        this(id, position, width, paint, new ShapeOffset());
    }

    public GridCell(int id, PointF position, int width) {
        this(id, position, width, null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void changeColorTo(int oolor) {
        Paint paint = new Paint();
        paint.setColor(oolor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint = paint;
    }

}

package com.lukas_tamz.braintrainer.models.shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

import com.lukas_tamz.braintrainer.models.BitmapParam;

/**
 * Created by ldockal on 11/24/2017.
 */

public abstract class Shape {

    private int id;
    private Bitmap texture;
    private Paint paint;
    private PointF position;


    public Shape(int id, BitmapParam bitmapParam, Paint paint, PointF position) {
        this.id = id;
        this.texture = Bitmap.createScaledBitmap(bitmapParam.getBitmap(), bitmapParam.getWidth(), bitmapParam.getHeight(), false);
        this.paint = paint;
        this.position = position;
    }


    public Shape(int id, Bitmap texture, PointF position) {
        this.id = id;
        this.texture = texture;
        this.position = position;
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.BLACK);
        defaultPaint.setStrokeWidth(1.0f);
        this.paint = defaultPaint;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(texture, new Matrix(), paint);
    }

    protected abstract boolean isPointIn(PointF point);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public void setTexture(Bitmap texture) {
        this.texture = texture;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }
}

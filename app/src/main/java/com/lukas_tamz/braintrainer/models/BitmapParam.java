package com.lukas_tamz.braintrainer.models;

import android.graphics.Bitmap;
import android.graphics.PointF;

/**
 * Created by ldockal on 11/24/2017.
 */

public final class BitmapParam {

    private Bitmap bitmap;
    private int width;
    private int height;
    private PointF position;

    public BitmapParam(Bitmap bitmap, int width, int height, PointF position) {
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public BitmapParam(Bitmap bitmap, int width, int height) {
        this(bitmap, width, height, new PointF(0.0f, 0.0f));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }
}

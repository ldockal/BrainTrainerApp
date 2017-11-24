package com.lukas_tamz.braintrainer.models.shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.lukas_tamz.braintrainer.models.BitmapParam;

/**
 * Created by ldockal on 11/24/2017.
 */

public class Square extends Shape {

    private int width;

    public Square(int id, BitmapParam bitmapParam, Paint paint, PointF position, int width) {
        super(id, bitmapParam, paint, position);
        this.width = width;
    }

    public Square(int id, Bitmap texture, PointF position, int width) {
        super(id, texture, position);
        this.width = width;
    }

    @Override
    protected boolean isPointIn(PointF point) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        RectF rectF = new RectF
                (
                        getPosition().x - width / 2,
                        getPosition().y - width / 2,
                        getPosition().x + width / 2,
                        getPosition().y + width / 2
                );
        canvas.drawRect(rectF, getPaint());
    }
}

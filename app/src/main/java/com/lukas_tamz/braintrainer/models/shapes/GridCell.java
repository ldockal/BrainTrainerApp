package com.lukas_tamz.braintrainer.models.shapes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.lukas_tamz.braintrainer.models.BitmapParam;

/**
 * Created by ldockal on 11/24/2017.
 */

public class GridCell extends Square {

    private boolean checked;

    public GridCell(int id, BitmapParam bitmapParam, Paint paint, PointF position, int width, boolean checked) {
        super(id, bitmapParam, paint, position, width);
        this.checked = checked;
    }

    public GridCell(int id, Bitmap texture, PointF position, int width, boolean checked) {
        super(id, texture, position, width);
        this.checked = checked;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

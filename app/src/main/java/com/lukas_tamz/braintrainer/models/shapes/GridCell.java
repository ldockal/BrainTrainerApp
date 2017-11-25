package com.lukas_tamz.braintrainer.models.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.lukas_tamz.braintrainer.models.BitmapParam;

/**
 * Created by ldockal on 11/24/2017.
 */

public class GridCell extends Shape {

    private boolean checked;

    public GridCell(int id, PointF position, int width, int height, Paint paint, ShapeOffset shapeOffset, BitmapParam bitmapParam, boolean checked) {
        super(id, position, width, height, paint, shapeOffset, bitmapParam);
        this.checked = checked;
    }

    public GridCell(int id, PointF position, int width, int height, Paint paint, ShapeOffset shapeOffset, boolean checked) {
        super(id, position, width, height, paint, shapeOffset);
        this.checked = checked;
    }

    public GridCell(int id, PointF position, int width, int height, Paint paint, boolean checked) {
        super(id, position, width, height, paint);
        this.checked = checked;
    }

    public GridCell(int id, PointF position, int width, int height, boolean checked) {
        super(id, position, width, height);
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

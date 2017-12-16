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
    private int incorrectColor = Color.RED;
    private int defaultColor = Color.argb(255, 26, 127, 146);
    private boolean cellIsInIdSequence;


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
        if (!isClicked()) {
            changeColorTo(defaultColor);
        } else if (isCellIsInIdSequence()) {
            changeColorTo(correctColor);
        } else {
            changeColorTo(incorrectColor);
        }
        super.draw(canvas);
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isCellIsInIdSequence() {
        return cellIsInIdSequence;
    }

    public void setCellIsInIdSequence(boolean cellIsInIdSequence) {
        this.cellIsInIdSequence = cellIsInIdSequence;
    }

    public int getCorrectColor() {
        return correctColor;
    }

    public void setCorrectColor(int correctColor) {
        this.correctColor = correctColor;
    }

    public int getIncorrectColor() {
        return incorrectColor;
    }

    public void setIncorrectColor(int incorrectColor) {
        this.incorrectColor = incorrectColor;
    }

    public int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    private void changeColorTo(int oolor) {
        Paint paint = new Paint();
        paint.setColor(oolor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint = paint;
    }
}

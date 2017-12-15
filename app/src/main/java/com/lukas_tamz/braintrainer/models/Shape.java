package com.lukas_tamz.braintrainer.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by ldockal on 11/24/2017.
 */

public abstract class Shape extends BaseModel {

    protected PointF position;
    protected int width;
    protected int height;
    protected Paint paint;
    protected ShapeOffset shapeOffset;
    protected BitmapParam bitmapParam;
    protected Bitmap texture;
    protected RectF shapeBorder;

    public Shape(int id, PointF position, int width, int height, Paint paint, ShapeOffset shapeOffset, BitmapParam bitmapParam) {
        super(id);
        this.position = position;
        this.width = width;
        this.height = height;
        this.paint = paint;
        this.shapeOffset = shapeOffset;
        this.bitmapParam = bitmapParam;
    }

    public Shape(int id, PointF position, int width, int height, Paint paint, ShapeOffset shapeOffset) {
        this(id, position, width, height, paint, shapeOffset, null);
    }

    public Shape(int id, PointF position, int width, int height, Paint paint) {
        this(id, position, width, height, paint, new ShapeOffset(), null);
    }

    public Shape(int id, PointF position, int width, int height) {
        this(id, position, width, height, new Paint(), new ShapeOffset(), null);
    }

    private void scaleTexture() {
        if (bitmapParam != null) {
            this.texture = Bitmap.createScaledBitmap(bitmapParam.getBitmap(), bitmapParam.getWidth(), bitmapParam.getHeight(), false);
        }

    }

    public void draw(Canvas canvas) {
        createBorder();
        scaleTexture();
        canvas.drawRect(shapeBorder, paint);
        if (texture != null) {
            canvas.drawBitmap(texture, new Matrix(), paint);
        }
    }

    public boolean isPointIn(PointF point) {
        return shapeBorder.contains(point.x, point.y);
    }

    private void createBorder() {
        this.shapeBorder = new RectF
                (
                        (this.position.x - width / 2) + shapeOffset.getLeft(),
                        (this.position.y - height / 2)+ shapeOffset.getTop(),
                        (this.position.x + width / 2)+ shapeOffset.getRight(),
                        (this.position.y + height / 2)+ shapeOffset.getBottom()
                );
    }


    public int getId() {
        return id;
    }

    public PointF getPosition() {
        return position;
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

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public ShapeOffset getShapeOffset() {
        return shapeOffset;
    }

    public BitmapParam getBitmapParam() {
        return bitmapParam;
    }

    public void setBitmapParam(BitmapParam bitmapParam) {
        this.bitmapParam = bitmapParam;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public RectF getShapeBorder() {
        return shapeBorder;
    }
}

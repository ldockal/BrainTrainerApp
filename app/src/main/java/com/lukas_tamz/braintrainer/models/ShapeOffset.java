package com.lukas_tamz.braintrainer.models;

/**
 * Created by ldockal on 11/24/2017.
 */

public final class ShapeOffset {
    private int top;
    private int right;
    private int bottom;
    private int left;

    public ShapeOffset(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public ShapeOffset() {
        this(0,0,0,0);
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }
}

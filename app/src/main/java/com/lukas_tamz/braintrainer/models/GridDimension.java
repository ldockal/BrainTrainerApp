package com.lukas_tamz.braintrainer.models;

/**
 * Created by ldockal on 11/24/2017.
 */

public final class GridDimension {

    private int rowSize;
    private int columnSize;

    public GridDimension(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public GridDimension() {
        this(0, 0);
    }

    public boolean isCorrectSet() {
        return rowSize >= 0 || columnSize >= 0;
    }

    public int getRowSize() {
        return rowSize;
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getSize() {
        return rowSize + columnSize;
    }

    @Override
    public String toString() {
        String sb = "GridDimension{" + "rowSize=" + rowSize +
                ", columnSize=" + columnSize +
                '}';
        return sb;
    }
}

package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 11/24/2017.
 */

public final class GridDimension implements Serializable {

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

    public int getNumberOfCells() {
        return rowSize * columnSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridDimension that = (GridDimension) o;
        return getRowSize() == that.getRowSize() &&
                getColumnSize() == that.getColumnSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowSize(), getColumnSize());
    }

    @Override
    public String toString() {
        return "GridDimension{" +
                "rowSize=" + rowSize +
                ", columnSize=" + columnSize +
                '}';
    }
}

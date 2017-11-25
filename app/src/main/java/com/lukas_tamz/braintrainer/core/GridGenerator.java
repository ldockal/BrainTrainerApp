package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.shapes.GridCell;

import java.util.Collection;

/**
 * Created by ldockal on 11/25/2017.
 */

public class GridGenerator implements Generator<GridCell> {

    @Override
    public Collection<GridCell> generateItems() {
        return null;
    }

    @Override
    public GridCell generateSingleItem() {
        throw new UnsupportedOperationException();
    }
}

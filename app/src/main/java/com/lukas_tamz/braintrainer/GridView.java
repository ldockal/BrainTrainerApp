package com.lukas_tamz.braintrainer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.lukas_tamz.braintrainer.exceptions.GridDimensionExcepion;
import com.lukas_tamz.braintrainer.models.GridDimension;
import com.lukas_tamz.braintrainer.models.shapes.GridCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldockal on 11/23/2017.
 */

public class GridView extends View {

    private List<GridCell> grid;
    private GridDimension dimension;


    public GridView(Context context) {
        super(context);
        grid = new ArrayList<>();
    }

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!dimension.isCorrectSet()) {
            throw new GridDimensionExcepion(dimension + " is not set correctly");
        } else {
            for (GridCell cell : grid) {
                cell.draw(canvas);
            }
        }
    }

    private void generateGrid() {
        //Todo - generate grid
    }


}

package com.lukas_tamz.braintrainer.models;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lukas_tamz.braintrainer.GameGridActivity;
import com.lukas_tamz.braintrainer.exceptions.GridDimensionExcepion;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ldockal on 11/23/2017.
 */

public class GridView extends View {

    private static final int cellWidth = 100;
    private List<GridCell> grid = new ArrayList<>();
    private List<Integer> idsToSelect = new ArrayList<>();
    private GridDimension dimension = new GridDimension(3, 3);
    private GameGridActivity gameGridActivity;


    public GridView(Context context) {
        super(context);

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

        if (dimension == null || !dimension.isCorrectSet()) {
            throw new GridDimensionExcepion(dimension + " is not set correctly");
        }
        if (grid == null || grid.isEmpty()) {
            generateGrid();
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        for (GridCell cell : grid) {
            cell.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            int column = (int) (event.getX() / cellWidth);
//            int row = (int) (event.getY() / cellWidth);
//            Log.i(TAG, "onTouchEvent: col: " + column + " row: " + row);
//            invalidate();

            for (GridCell cell : grid) {

                if (cell.isPointIn(new PointF(event.getX(), event.getY()))) {
                    Log.i(TAG, "cell with id : " + cell.getId() + " was clicked");

                    if (!cell.isClicked()) {
                        if (idsToSelect.contains(cell.getId())) {

                            Log.i(TAG, "clicked cell with id : " + cell.getId() + " is in list");
                            cell.changeColorTo(Color.GREEN);
                            idsToSelect.remove(idsToSelect.indexOf(cell.getId()));

                        } else {
                            Log.i(TAG, "clicked cell with id : " + cell.getId() + " is not in list");
                            cell.changeColorTo(Color.RED);
                            gameGridActivity.decreaseRepeats();
                        }

                        cell.setClicked(true);
                    }
                }
            }

            if (idsToSelect.isEmpty()) {
                Log.i(TAG, "level ended");
                gameGridActivity.handleNextLevel();
                return true;
            }

            invalidate();
        }

        return true;
    }


    public void startNewLevel() {

    }

    private void generateGrid() {

        int gap = 10;
        int actualCol = 1;
        int actualRow = 1;
        float posX = cellWidth + gap;
        float posY = cellWidth + gap;

        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 26, 127, 146));
        paint.setStyle(Paint.Style.FILL);

        while (actualRow <= dimension.getRowSize()) {

            grid.add(new GridCell(actualCol, new PointF(posX, posY), cellWidth, paint, new ShapeOffset()));

            if (actualCol % dimension.getColumnSize() == 0) {
                actualRow++;
                posX = cellWidth + gap;
                posY += cellWidth + gap;
            } else {
                posX += cellWidth + gap;
            }

            actualCol++;
        }
    }

    private void showCells() {

        for (Integer id : idsToSelect) {
            grid.get(id).changeColorTo(Color.GREEN);
        }

        invalidate();
    }

    public GridDimension getDimension() {
        return dimension;
    }

    public void setDimension(GridDimension dimension) {
        this.dimension = dimension;
        generateGrid();
    }

    public GameGridActivity getGameGridActivity() {
        return gameGridActivity;
    }

    public void setGameGridActivity(GameGridActivity gameGridActivity) {
        this.gameGridActivity = gameGridActivity;
    }

    public List<Integer> getIdsToSelect() {
        return idsToSelect;
    }

    public void setIdsToSelect(List<Integer> idsToSelect) {
        this.idsToSelect = idsToSelect;
    }
}

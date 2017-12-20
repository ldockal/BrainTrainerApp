package com.lukas_tamz.braintrainer.models;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lukas_tamz.braintrainer.GameGridActivity;
import com.lukas_tamz.braintrainer.exceptions.GridDimensionExcepion;
import com.lukas_tamz.braintrainer.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ldockal on 11/23/2017.
 */

public class GridView extends View {

    private int cellWidth = 100;
    private List<GridCell> grid = new ArrayList<>();
    private List<Integer> idsToSelect = new ArrayList<>();
    private GridDimension dimension = new GridDimension(3, 3);
    private GameGridActivity gameGridActivity;
    private boolean gridTouchEnabled = true;
    private boolean clearCanvas = false;
    private int canvasWidth;
    private int canvasHeight;
    private SharedPreferenceHelper preferenceHelper;

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
        this.canvasHeight = h;
        this.canvasWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (clearCanvas) {
            int color = Color.argb(255, 128, 223, 242);

            canvas.drawColor(color);
            clearCanvas = false;
        } else {
            if (dimension == null || !dimension.isCorrectSet()) {
                throw new GridDimensionExcepion(dimension + " is not set correctly");
            }
            if (grid == null || grid.isEmpty()) {
                generateGrid();
            }

            for (GridCell cell : grid) {
                cell.draw(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gridTouchEnabled) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                for (GridCell cell : grid) {

                    if (cell.isPointIn(new PointF(event.getX(), event.getY()))) {
                        Log.i(TAG, "cell with id : " + cell.getId() + " was clicked");

                        if (!cell.isClicked()) {
                            if (cell.isCellIsInIdSequence()) {
                                idsToSelect.remove(Integer.valueOf(cell.getId()));
                            } else {
                                gameGridActivity.decreaseRepeats();
                            }
                            cell.setClicked(true);
                            break;
                        }
                    }
                }

                invalidate();

                if (idsToSelect.isEmpty()) {
                    //clear canvas
                    clearCanvas = true;
                    invalidate();
                    gameGridActivity.handleNextLevel();
                    return true;
                }


            }
        }

        return true;
    }


    public void startLevel() {
        clearCanvas = false;
        grid = new ArrayList<>();
        generateGrid();
        showCorrectCells();
    }

    private void generateGrid() {

        int cellCount = 1;
        int gap = 10;
        int actualCol = 1;
        int actualRow = 1;

        if ((dimension.getColumnSize() == 5 && dimension.getColumnSize() == 5)) {

            cellWidth = 75;
        }

        float posX = cellWidth + gap;
        float posY = cellWidth + gap;

        Paint paint = new Paint();
        paint.setColor(Color.argb(255, 26, 127, 146));
        paint.setStyle(Paint.Style.FILL);

        while (actualRow <= dimension.getRowSize()) {

            GridCell cell = new GridCell(actualCol, new PointF(posX, posY), cellWidth, paint, new ShapeOffset());
            if (idsToSelect.contains(cellCount)) {
                cell.setCellIsInIdSequence(true);
            } else {
                cell.setCellIsInIdSequence(false);
            }
            grid.add(cell);

            if (actualCol % dimension.getColumnSize() == 0) {
                actualRow++;
                posX = cellWidth + gap;
                posY += cellWidth + gap;
            } else {
                posX += cellWidth + gap;
            }

            actualCol++;
            cellCount++;
        }
    }

    private void displayCorrectCell(boolean display) {
        for (GridCell gridCell : grid) {
            if (gridCell.isCellIsInIdSequence()) {
                gridCell.setClicked(display);
            }
        }
    }

    private void showCorrectCells() {
        gridTouchEnabled = false;
        displayCorrectCell(true);
        invalidate();

        // wait some time and then hide correct cells back
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayCorrectCell(false);
                gridTouchEnabled = true;
                invalidate();
            }
        }, 1400);
    }

    public GridDimension getDimension() {
        return dimension;
    }

    public void setDimension(GridDimension dimension) {
        this.dimension = dimension;
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

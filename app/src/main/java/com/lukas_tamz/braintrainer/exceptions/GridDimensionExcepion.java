package com.lukas_tamz.braintrainer.exceptions;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.lukas_tamz.braintrainer.models.GridDimension;

/**
 * Created by ldockal on 11/24/2017.
 */

public class GridDimensionExcepion extends RuntimeException{

    public GridDimensionExcepion() {
    }

    public GridDimensionExcepion(String message) {
        super(message);
    }

    public GridDimensionExcepion(String message, Throwable cause) {
        super(message, cause);
    }

    public GridDimensionExcepion(Throwable cause) {
        super(cause);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public GridDimensionExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public GridDimensionExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, GridDimension dimension) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

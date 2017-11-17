package com.lukas_tamz.braintrainer.utils;

import android.content.Context;

/**
 * Created by ldockal on 11/17/2017.
 */

public final class Utils {
    private Utils() {
    }

    public static int getImageIdFromDrawable(Context context, String imgName) {
        return context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
    }
}

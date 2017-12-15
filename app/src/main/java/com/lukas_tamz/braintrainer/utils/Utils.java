package com.lukas_tamz.braintrainer.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ldockal on 11/17/2017.
 */

public final class Utils {
    private Utils() {
    }

    public static int getImageIdFromDrawable(Context context, String imgName) {
        return context.getResources().getIdentifier(imgName, "drawable", context.getPackageName());
    }

    public static void displayintToast(Activity activity, String text) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public static int getNumberInRane(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}

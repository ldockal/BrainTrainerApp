package com.lukas_tamz.braintrainer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ldockal on 12/17/2017.
 */

public final class SharedPreferenceHelper {


    private final Context context;
    private final String preferenceName;
    private final int mode;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context, String preferenceName, int mode) {
        this.context = context;
        this.preferenceName = preferenceName;
        this.mode = mode;
        sharedPreferences = context.getSharedPreferences(preferenceName, mode);
    }

    public void setSharedPreferenceString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setSharedPreferenceInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setSharedPreferenceBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public String getSharedPreferenceString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    public int getSharedPreferenceInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public boolean getSharedPreferenceBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }
}

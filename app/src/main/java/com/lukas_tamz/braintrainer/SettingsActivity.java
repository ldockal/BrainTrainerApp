package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.lukas_tamz.braintrainer.holders.ConstantsHolder;
import com.lukas_tamz.braintrainer.utils.SharedPreferenceHelper;

public class SettingsActivity extends Activity {
    private Switch enableVibrationswitch;
    private Button saveButton;
    private SharedPreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadComponents();

        preferenceHelper = new SharedPreferenceHelper(getApplicationContext(), ConstantsHolder.SETTINGS_PREF, Context.MODE_PRIVATE);
        enableVibrationswitch.setChecked(preferenceHelper.getSharedPreferenceBoolean(ConstantsHolder.SETTINGS_PREF_VIBRATION, false));
    }

    private void loadComponents() {
        enableVibrationswitch = findViewById(R.id.enableVibrationswitch);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean vibration = enableVibrationswitch.isChecked();

                preferenceHelper.setSharedPreferenceBoolean(ConstantsHolder.SETTINGS_PREF_VIBRATION, vibration);

                Intent intent = new Intent(getApplicationContext(), MiniGamesActivity.class);
                startActivity(intent);
            }
        });
    }
}

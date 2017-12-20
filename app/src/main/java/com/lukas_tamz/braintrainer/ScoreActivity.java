package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.db.dao.api.ScoreDao;
import com.lukas_tamz.braintrainer.db.dao.impl.ScoreDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends Activity {

    private Spinner gamesSpinner;
    private List<TextView> scorePositions;
    private ScoreDao scoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreDao = new ScoreDaoImpl(getApplicationContext());

        List<String> games = new ArrayList<>();

        games.add("1");
        games.add("2");
        games.add("3");

        loadComponents();

        initSpinner(games);
    }

    private void initSpinner(List<String> games) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, games);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww
        gamesSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void gotoMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadComponents() {
        scorePositions = new ArrayList<>();

        TextView textView1 = findViewById(R.id.scoreTextView1);
        TextView textView2 = findViewById(R.id.scoreTextView2);
        TextView textView3 = findViewById(R.id.scoreTextView3);

        scorePositions.add(textView1);
        scorePositions.add(textView2);
        scorePositions.add(textView3);

        gamesSpinner = findViewById(R.id.gamesSpinner);
    }
}

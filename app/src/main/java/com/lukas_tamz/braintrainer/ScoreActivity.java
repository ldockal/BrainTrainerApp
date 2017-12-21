package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.db.dao.api.ScoreDao;
import com.lukas_tamz.braintrainer.db.dao.impl.ScoreDaoImpl;
import com.lukas_tamz.braintrainer.db.entities.ScoreEntity;
import com.lukas_tamz.braintrainer.holders.ConstantsHolder;
import com.lukas_tamz.braintrainer.models.SimpleObject;
import com.lukas_tamz.braintrainer.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends Activity {

    private Spinner gamesSpinner;
    private List<TextView> scorePositions;
    private ScoreDao scoreDao;
    private SharedPreferenceHelper preferenceHelper;
    private List<SimpleObject<Integer>> games;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        preferenceHelper = new SharedPreferenceHelper(this, ConstantsHolder.GAMES_PREF_NAMES, Context.MODE_PRIVATE);
        scoreDao = new ScoreDaoImpl(getApplicationContext());

        loadComponents();

        games = new ArrayList<>();

        String gameCompresStr = preferenceHelper.getSharedPreferenceString(ConstantsHolder.GAMES_PREF_NAMES_KEY, "");

        if (!gameCompresStr.equals("")) {
            String gamesNamesithId[] = gameCompresStr.split("::");

            for (int i = 0; i < gamesNamesithId.length; i++) {
                String x[] = gamesNamesithId[i].split("//");
                SimpleObject<Integer> gameObj = new SimpleObject<>();
                gameObj.setId(Integer.parseInt(x[0]));
                gameObj.setName(x[1]);
                games.add(gameObj);

            }
        }

        initSpinner(getListOfNamesForSpinner());
    }

    private List<String> getListOfNamesForSpinner() {
        List<String> gameNames = new ArrayList<>();

        if (games.size() > 0) {
            for (SimpleObject simpleObject : games) {
                gameNames.add(simpleObject.getName());
            }
        }

        return gameNames;
    }

    private void initSpinner(List<String> games) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, games);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        gamesSpinner.setAdapter(spinnerArrayAdapter);
    }

    public void gotoMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearScoreView() {
        for (TextView textView : scorePositions) {
            textView.setText("");
        }
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

        gamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                clearScoreView();
                SimpleObject<Integer> simpleObject = games.get(position);
                List<ScoreEntity> scoreEntities = scoreDao.getScoresByGameId(simpleObject.getId());

                int pos = 0;
                for (TextView textView : scorePositions) {

                    if (scoreEntities.size() == 0){
                      textView.setText("");
                    }
                    else if (pos < scoreEntities.size()) {
                        String text = "Level: " + scoreEntities.get(pos).getScore();
                        textView.setText(text);
                        pos++;
                    } else {
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

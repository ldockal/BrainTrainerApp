package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.holders.ConstantsHolder;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.models.GameStatus;
import com.lukas_tamz.braintrainer.models.GridDimension;
import com.lukas_tamz.braintrainer.models.GridView;
import com.lukas_tamz.braintrainer.utils.SharedPreferenceHelper;
import com.lukas_tamz.braintrainer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GameGridActivity extends Activity {

    public static final int ROW_SIZE = 3;
    public static final int COLUMN_SIZE = 3;

    private TextView gameNameTextView;
    private TextView actualLevelTextView;
    private TextView maxRepeatsTextView;
    private ProgressBar progressBar;
    private GameStatus gameStatus;
    private GridView gridView;
    private SharedPreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_grid);

        preferenceHelper = new SharedPreferenceHelper(getApplicationContext(), ConstantsHolder.SETTINGS_PREF, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        GameInfo receivedGameInfo = (GameInfo) intent.getSerializableExtra(GameInfo.NAME);

        if (receivedGameInfo != null) {
            gameStatus = new GameStatus(receivedGameInfo.getTitle(), 1, receivedGameInfo.getMaxRepeats());
            gameStatus.setGameId(receivedGameInfo.getId());
            loadAndInitComponents();

            gridView = findViewById(R.id.gridView);
            prepareInitialLevel();
            gridView.setGameGridActivity(this);


            //gridView.setDimension(new GridDimension(ROW_SIZE, COLUMN_SIZE));
        } else {
            throw new NullPointerException("recived game ifo object is null, game can not be loaded.");
        }
    }

    public void handleNextLevel() {
        gameStatus.increaseLevel();

        String actLevel = "Level: " + String.valueOf(gameStatus.getLevel());
        actualLevelTextView.setText(actLevel);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // every second level increase grid until level 5
                if (gameStatus.getLevel() < 5 && gameStatus.getLevel() % 2 == 0) {
                    gridView.setDimension(new GridDimension(
                            gridView.getDimension().getRowSize() + 1,
                            gridView.getDimension().getColumnSize() + 1));
                }
                gridView.setIdsToSelect(generateIdsToSelect());
                gridView.startLevel();
            }
        }, 1000);


    }

    public void decreaseRepeats() {
        gameStatus.decreaseRepeats();
        boolean useVibrator = preferenceHelper.getSharedPreferenceBoolean(ConstantsHolder.SETTINGS_PREF_VIBRATION, false);
        if (useVibrator) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator != null) {
                vibrator.vibrate(500);
            }
        }
        if (gameStatus.getRemainingRepeats() == 0) {
            // game over
            Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
            intent.putExtra(GameStatus.NAME, gameStatus);
            startActivity(intent);
        }

        String maxRep = "Pocet pokusu: " + String.valueOf(gameStatus.getRemainingRepeats());
        maxRepeatsTextView.setText(maxRep);
    }


    private void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    private void loadAndInitComponents() {

        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        gameNameTextView = linearLayout.findViewById(R.id.gameNameTextView);
        actualLevelTextView = linearLayout.findViewById(R.id.actualLevelTextView);
        maxRepeatsTextView = linearLayout.findViewById(R.id.maxRepeatsTextView);
        progressBar = findViewById(R.id.timeProgressBar);

        String gameName = "Hra: " + gameStatus.getGameName();
        String level = "Level: " + gameStatus.getLevel();
        String repeats = "Pocet opakovani: " + gameStatus.getRemainingRepeats();

        gameNameTextView.setText(gameName);
        actualLevelTextView.setText(level);
        maxRepeatsTextView.setText(repeats);
    }

    private List<Integer> generateIdsToSelect() {
        List<Integer> toReturn = new ArrayList<>();
        // todo get this number dynamically based on current level
        int idsToGenerate = 4;
        int totalCells = gridView.getDimension().getNumberOfCells();

        while (idsToGenerate > 0) {

            int generatedId = Utils.getNumberInRange(1, totalCells);

            if (!toReturn.contains(generatedId)) {
                toReturn.add(generatedId);
                idsToGenerate--;
            }
        }

        Log.i(TAG, "generateIdsToSelect: " + toReturn.toString());
        return toReturn;
    }

    private void prepareInitialLevel() {
        gridView.setDimension(new GridDimension(ROW_SIZE, COLUMN_SIZE));
        gridView.setIdsToSelect(generateIdsToSelect());
        gridView.startLevel();
    }
}

package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.models.GameStatus;
import com.lukas_tamz.braintrainer.models.GridDimension;
import com.lukas_tamz.braintrainer.models.GridView;
import com.lukas_tamz.braintrainer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class GameGridActivity extends Activity {

    public static final int ROW_SIZE = 3;
    public static final int COLUMN_SIZE = 3;

    public TextView gameNameTextView;
    public TextView actualLevelTextView;
    public TextView maxRepeatsTextView;
    private GameStatus gameStatus;
    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        GameInfo receivedGameInfo = (GameInfo) intent.getSerializableExtra(GameInfo.NAME);

        if (receivedGameInfo != null) {
            gameStatus = new GameStatus(receivedGameInfo.getTitle(), 1, receivedGameInfo.getMaxRepeats());
            loadAndInitComponents();

            gridView = findViewById(R.id.gridView);
            gridView.setGameGridActivity(this);
            gridView.setDimension(new GridDimension(ROW_SIZE, COLUMN_SIZE));


        } else {
            throw new NullPointerException("recived game ifo object is null, game can not be loaded.");
        }

        setContentView(R.layout.activity_game_grid);
    }

    public void handleNextLevel() {
        gameStatus.increaseLevel();

        String actLevel = "Level: " + String.valueOf(gameStatus.getLevel());
        actualLevelTextView.setText(actLevel);

        // every second level increase grid until level 5
        if (gameStatus.getLevel() < 5 && gameStatus.getLevel() % 2 == 0) {
            gridView.setDimension(new GridDimension(
                    gridView.getDimension().getRowSize() + 1,
                    gridView.getDimension().getColumnSize() + 1));
        }
        gridView.setDimension(new GridDimension());
        gridView.setIdsToSelect(generateIdsToSelect());
        gridView.startNewLevel();
    }

    public void decreaseRepeats() {
        gameStatus.decreaseRepeats();

        if (gameStatus.getRemainingRepeats() == 0) {
            // game over
            Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
            intent.putExtra(GameStatus.NAME, GameStatus.class);
            startActivity(intent);
        }

        String maxRep = "Pocet pokusu: " + String.valueOf(gameStatus.getRemainingRepeats());
        maxRepeatsTextView.setText(maxRep);
    }


    private void loadAndInitComponents() {
        gameNameTextView = findViewById(R.id.gameNameTextView);
        actualLevelTextView = findViewById(R.id.actualLevelTextView);
        maxRepeatsTextView = findViewById(R.id.maxRepeatsTextView);

        gameNameTextView.setText(gameStatus.getGameName());
        actualLevelTextView.setText(gameStatus.getLevel());
        maxRepeatsTextView.setText(String.valueOf(gameStatus.getRemainingRepeats()));
    }

    private List<Integer> generateIdsToSelect() {
        List<Integer> toReturn = new ArrayList<>();
        // todo get this number dynamically based on current level
        int idsToGenerate = 4;
        int totalCells = gridView.getDimension().getNumberOfCells();

        while (idsToGenerate > 0) {

            int generatedId = Utils.getNumberInRane(1, totalCells);

            if (!toReturn.contains(generatedId)) {
                toReturn.add(generatedId);
                idsToGenerate--;
            }
        }

        return toReturn;
    }
}

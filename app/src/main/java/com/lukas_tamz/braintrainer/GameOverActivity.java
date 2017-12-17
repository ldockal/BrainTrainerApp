package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.db.dao.api.ScoreDao;
import com.lukas_tamz.braintrainer.db.dao.impl.ScoreDaoImpl;
import com.lukas_tamz.braintrainer.models.GameStatus;

public class GameOverActivity extends Activity {

    public static final String SCORE = "Score:";
    private TextView gameNameTextView;
    private TextView scoreTextView;
    private TextView scoreLabel;
    private Button menuButton;
    private ScoreDao scoreDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        scoreDao = new ScoreDaoImpl(getApplicationContext());
        initComponents();

        Intent intent = getIntent();
        GameStatus status = (GameStatus) intent.getSerializableExtra(GameStatus.NAME);

        if (status != null) {
            gameNameTextView.setText(status.getGameName());
            scoreTextView.setText(String.valueOf(status.getLevel()));
            /*
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setGameId(status.getGameId());
            scoreEntity.setScore(status.getLevel());
            scoreDao.saveScore(scoreEntity)
            */
        } else {
            throw new NullPointerException("recived game status is null.");
        }
    }

    private void initComponents() {
        gameNameTextView = findViewById(R.id.gameNameTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        menuButton = findViewById(R.id.menuButton);
        scoreLabel = findViewById(R.id.scoreLabel);
        scoreLabel.setText(SCORE);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to main activity
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

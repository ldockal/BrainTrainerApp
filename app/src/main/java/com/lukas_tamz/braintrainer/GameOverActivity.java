package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.models.GameStatus;

public class GameOverActivity extends Activity {

    private TextView gameNameTextView;
    private TextView scoreTextView;
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initComponents();

        Intent intent = getIntent();
        GameStatus status = (GameStatus) intent.getSerializableExtra(GameStatus.NAME);

        gameNameTextView.setText(status.getGameName());
        scoreTextView.setText(status.getLevel());

        setContentView(R.layout.activity_game_over);
    }

    private void initComponents() {
        gameNameTextView = findViewById(R.id.gameNameTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        menuButton = findViewById(R.id.menuButton);

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

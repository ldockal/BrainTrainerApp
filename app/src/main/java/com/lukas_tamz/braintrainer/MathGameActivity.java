package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.core.EquationGeneratorContext;
import com.lukas_tamz.braintrainer.core.MinusEquationGenerator;
import com.lukas_tamz.braintrainer.core.MultiplyEquationGenerator;
import com.lukas_tamz.braintrainer.core.PlusEquationGenerator;
import com.lukas_tamz.braintrainer.models.Equation;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.models.GameStatus;
import com.lukas_tamz.braintrainer.utils.Utils;

public class MathGameActivity extends Activity {

    public static final String INCORRECT = "incorrect";
    public static final String CORRECT = "correct";
    private TextView gameNameTextView;
    private TextView actualLevelTextView;
    private TextView maxRepeatsTextView;
    private Button correctButton;
    private Button incorrectButton;
    private ProgressBar timeProgressBar;
    private GameStatus gameStatus;
    private Equation equation;
    private char[] operation = {'+', '-', '*'};
    private EquationGeneratorContext equationGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        Intent intent = getIntent();

        GameInfo gameInfo = (GameInfo) intent.getSerializableExtra(GameInfo.NAME);

        gameStatus = new GameStatus(gameInfo.getTitle(), 1, gameInfo.getMaxRepeats());


    }

    private void checkUserClickAnswer(String button) {

    }

    private void genereteNewEquation() {
        equation = new Equation();
        int index;

        // generate operation
        index = Utils.getNumberInRange(1, operation.length);
        equation.setOperation(operation[index]);

        switch (operation[index]) {
            case '*':
                equationGenerator = new EquationGeneratorContext(new MultiplyEquationGenerator());
                break;
            case '-':
                equationGenerator = new EquationGeneratorContext(new MinusEquationGenerator());
                break;
            case '+':
                equationGenerator = new EquationGeneratorContext(new PlusEquationGenerator());
                break;
        }

        equation = equationGenerator.executeGenerationForStrategy(gameStatus.getLevel());
    }


    private void loadAndInitComponents() {

        gameNameTextView = findViewById(R.id.gameNameTextView);
        actualLevelTextView = findViewById(R.id.actualLevelTextView);
        maxRepeatsTextView = findViewById(R.id.maxRepeatsTextView);
        timeProgressBar = findViewById(R.id.timeProgressBar);
        correctButton = findViewById(R.id.correctButton);
        incorrectButton = findViewById(R.id.incorrectButton);
        timeProgressBar = findViewById(R.id.timeProgressBar);

        String gameName = "Hra: " + gameStatus.getGameName();
        String level = "Level: " + gameStatus.getLevel();
        String repeats = "Pocet opakovani: " + gameStatus.getRemainingRepeats();

        gameNameTextView.setText(gameName);
        actualLevelTextView.setText(level);
        maxRepeatsTextView.setText(repeats);

        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserClickAnswer(INCORRECT);
            }
        });

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserClickAnswer(CORRECT);
            }
        });
    }
}

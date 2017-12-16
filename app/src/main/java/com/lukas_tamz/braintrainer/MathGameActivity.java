package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private TextView equationTextView;
    private Button correctButton;
    private Button incorrectButton;
    private ProgressBar timeProgressBar;
    private GameStatus gameStatus;
    private Equation equation;
    private char[] operation = {'+', '-', '*'};
    private EquationGeneratorContext equationGenerator;
    private int maxSecToSolveEquation = 12;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        Intent intent = getIntent();
        GameInfo gameInfo = (GameInfo) intent.getSerializableExtra(GameInfo.NAME);
        gameStatus = new GameStatus(gameInfo.getTitle(), 1, gameInfo.getMaxRepeats());

        loadAndInitComponents();
        generateNewEquation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearCountDown();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearCountDown();
    }

    private void checkUserClickAnswer(String button) {

        if (equation.isCorrect() && button.equals(CORRECT) || !equation.isCorrect() && button.equals(INCORRECT)) {
            increaseLevel();
        } else {
            decreaseMaxReps();
        }
        clearCountDown();
        generateNewEquation();
    }

    private void increaseLevel() {
        gameStatus.increaseLevel();
        String s = "Level: " + gameStatus.getLevel();
        actualLevelTextView.setText(s);
    }

    private void clearCountDown() {
        countDownTimer.cancel();
        countDownTimer = null;
    }

    private void decreaseMaxReps() {
        gameStatus.decreaseRepeats();

        //game over
        if (gameStatus.getRemainingRepeats() == 0) {
            clearCountDown();
            Intent intent = new Intent(getBaseContext(), GameOverActivity.class);
            intent.putExtra(GameStatus.NAME, gameStatus);
            startActivity(intent);
        }

        String s = "Pocet opakovani: " + gameStatus.getRemainingRepeats();
        maxRepeatsTextView.setText(s);
    }

    private void generateNewEquation() {
        equation = new Equation();
        int index;

        // generate operation
        index = Utils.getNumberInRange(1, operation.length - 1);
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
        equationTextView.setText(equation.toString());

        timeProgressBar.setProgress(100);
        final int progressPerSec = 100 / maxSecToSolveEquation;

        // start countdown time\
        countDownTimer = new CountDownTimer(maxSecToSolveEquation * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int actProgress = timeProgressBar.getProgress();
                timeProgressBar.setProgress(actProgress - progressPerSec);
            }

            @Override
            public void onFinish() {
                timeProgressBar.setProgress(0);
                decreaseMaxReps();
                generateNewEquation();
            }
        }.start();
    }


    private void loadAndInitComponents() {

        gameNameTextView = findViewById(R.id.gameNameTextView);
        actualLevelTextView = findViewById(R.id.actualLevelTextView);
        maxRepeatsTextView = findViewById(R.id.maxRepeatsTextView);
        timeProgressBar = findViewById(R.id.timeProgressBar);
        correctButton = findViewById(R.id.correctButton);
        incorrectButton = findViewById(R.id.incorrectButton);
        timeProgressBar = findViewById(R.id.timeProgressBar);
        equationTextView = findViewById(R.id.equationTextView);

        String gameName = "Hra: " + gameStatus.getGameName();
        String level = "Level: " + gameStatus.getLevel();
        String repeats = "Pocet opakovani: " + gameStatus.getRemainingRepeats();

        gameNameTextView.setText(gameName);
        actualLevelTextView.setText(level);
        maxRepeatsTextView.setText(repeats);

        incorrectButton.setText("Nespravne");
        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserClickAnswer(INCORRECT);
            }
        });

        correctButton.setText("Spravne");
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserClickAnswer(CORRECT);
            }
        });
    }
}

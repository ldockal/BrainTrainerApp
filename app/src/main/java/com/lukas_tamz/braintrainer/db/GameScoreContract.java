package com.lukas_tamz.braintrainer.db;

import android.provider.BaseColumns;

/**
 * Created by ldockal on 12/17/2017.
 */

public final class GameScoreContract {

    private GameScoreContract() {
    }

    public static class ScoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "score";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_GAME_ID = "gameId";
    }
}

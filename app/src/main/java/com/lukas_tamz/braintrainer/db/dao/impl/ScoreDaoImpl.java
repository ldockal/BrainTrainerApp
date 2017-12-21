package com.lukas_tamz.braintrainer.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lukas_tamz.braintrainer.db.GameScoreContract;
import com.lukas_tamz.braintrainer.db.dao.BaseDao;
import com.lukas_tamz.braintrainer.db.dao.api.ScoreDao;
import com.lukas_tamz.braintrainer.db.entities.ScoreEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldockal on 12/17/2017.
 */

public class ScoreDaoImpl extends BaseDao implements ScoreDao {

    public ScoreDaoImpl(Context context) {
        super(context);
    }

    @Override
    public List<ScoreEntity> getScoresByGameId(int id) {
        List<ScoreEntity> scoreEntities = new ArrayList<>();

        Cursor cursor = database.query(GameScoreContract.ScoreEntry.TABLE_NAME,
                new String[]{GameScoreContract.ScoreEntry._ID,
                        GameScoreContract.ScoreEntry.COLUMN_NAME_GAME_ID,
                        GameScoreContract.ScoreEntry.COLUMN_NAME_LEVEL},
                null, null, null, null, GameScoreContract.ScoreEntry.COLUMN_NAME_LEVEL + " DESC");

        while (cursor.moveToNext()) {
            ScoreEntity score = new ScoreEntity();
            score.setId(cursor.getInt(0));
            score.setGameId(cursor.getInt(1));
            score.setScore(cursor.getInt(2));

            // todo delete this it is only for testing
            if (score.getGameId() == id) {
                scoreEntities.add(score);
            }
        }
        return scoreEntities;
    }

    @Override
    public long saveScore(ScoreEntity scoreEntity) {
        ContentValues values = new ContentValues();
        values.put(GameScoreContract.ScoreEntry.COLUMN_NAME_LEVEL, scoreEntity.getScore());
        values.put(GameScoreContract.ScoreEntry.COLUMN_NAME_GAME_ID, scoreEntity.getGameId());
        return database.insert(GameScoreContract.ScoreEntry.TABLE_NAME, null, values);
    }
}

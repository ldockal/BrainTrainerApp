package com.lukas_tamz.braintrainer.db.dao.api;

import com.lukas_tamz.braintrainer.db.entities.ScoreEntity;

import java.util.List;

/**
 * Created by ldockal on 12/17/2017.
 */

public interface ScoreDao {

    List<ScoreEntity> getScoresByGameId(int id);

    long saveScore(ScoreEntity scoreEntity);
}

package com.lukas_tamz.braintrainer.db.entities;

import java.util.Objects;

/**
 * Created by ldockal on 12/17/2017.
 */

public class ScoreEntity extends BaseEntity {

    private int gameId;
    private int score;

    public ScoreEntity() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ScoreEntity that = (ScoreEntity) o;
        return getGameId() == that.getGameId() &&
                getScore() == that.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGameId(), getScore());
    }
}

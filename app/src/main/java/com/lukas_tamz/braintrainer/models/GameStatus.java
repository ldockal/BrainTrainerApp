package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 12/15/2017.
 */

public class GameStatus implements Serializable {

    public static final String NAME = "gameStatus";

    private int gameId;
    private String gameName;
    private int level;
    private int remainingRepeats;


    public GameStatus() {
    }

    public GameStatus(String gameName, int level, int remainingRepeats) {
        this.gameName = gameName;
        this.level = level;
        this.remainingRepeats = remainingRepeats;
    }

    public String getGameName() {
        return gameName;
    }

    public int getLevel() {
        return level;
    }

    public int getRemainingRepeats() {
        return remainingRepeats;
    }
    public void increaseLevel() {
        this.level++;
    }

    public void decreaseRepeats() {
        this.remainingRepeats--;
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
        GameStatus status = (GameStatus) o;
        return getGameId() == status.getGameId() &&
                getLevel() == status.getLevel() &&
                getRemainingRepeats() == status.getRemainingRepeats() &&
                Objects.equals(getGameName(), status.getGameName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getGameName(), getLevel(), getRemainingRepeats());
    }
}

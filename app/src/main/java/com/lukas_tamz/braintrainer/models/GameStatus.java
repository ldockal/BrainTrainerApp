package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 12/15/2017.
 */

public class GameStatus implements Serializable {

    public static final String NAME = "gameStatus";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStatus that = (GameStatus) o;
        return getLevel() == that.getLevel() &&
                getRemainingRepeats() == that.getRemainingRepeats() &&
                Objects.equals(getGameName(), that.getGameName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameName(), getLevel(), getRemainingRepeats());
    }
}

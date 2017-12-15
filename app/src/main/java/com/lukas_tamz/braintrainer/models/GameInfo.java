package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 11/17/2017.
 */

public class GameInfo implements Serializable {

    public static final String NAME = "gameInfo";

    private String id;
    private String title;
    private String desc;
    private String imgName;
    private int maxRepeats;

    public GameInfo(String id, String title, String desc, String imgName) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imgName = imgName;
    }

    public GameInfo() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxRepeats() {
        return maxRepeats;
    }

    public void setMaxRepeats(int maxRepeats) {
        this.maxRepeats = maxRepeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInfo gameInfo = (GameInfo) o;
        return getMaxRepeats() == gameInfo.getMaxRepeats() &&
                Objects.equals(getId(), gameInfo.getId()) &&
                Objects.equals(getTitle(), gameInfo.getTitle()) &&
                Objects.equals(getDesc(), gameInfo.getDesc()) &&
                Objects.equals(getImgName(), gameInfo.getImgName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDesc(), getImgName(), getMaxRepeats());
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imgName='" + imgName + '\'' +
                ", maxRepeats=" + maxRepeats +
                '}';
    }
}
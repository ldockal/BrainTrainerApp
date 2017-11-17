package com.lukas_tamz.braintrainer;

/**
 * Created by ldockal on 11/17/2017.
 */

public class Game {

    private String title;
    private String desc;
    private String imgName;

    public Game(String title, String desc, String imgName) {
        this.title = title;
        this.desc = desc;
        this.imgName = imgName;
    }

    public Game() {}

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
}
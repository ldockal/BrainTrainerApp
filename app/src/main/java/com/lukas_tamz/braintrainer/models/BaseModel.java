package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;

/**
 * Created by ldockal on 11/25/2017.
 */

public class BaseModel implements Serializable {
    protected final int id;

    BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

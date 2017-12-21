package com.lukas_tamz.braintrainer.models;

/**
 * Created by ldockal on 12/21/2017.
 */

public class SimpleObject<T extends Object> {

    private T id;
    private String name;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

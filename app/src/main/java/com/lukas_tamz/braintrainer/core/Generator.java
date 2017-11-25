package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.shapes.BaseModel;

import java.util.Collection;

/**
 * Created by ldockal on 11/25/2017.
 */

public interface Generator<T extends BaseModel> {

    Collection<T> generateItems();

    T generateSingleItem();
}

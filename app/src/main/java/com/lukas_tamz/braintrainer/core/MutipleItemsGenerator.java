package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.BaseModel;

import java.util.Collection;

/**
 * Created by ldockal on 11/25/2017.
 */

public interface MutipleItemsGenerator<T extends BaseModel> {

    Collection<T> generateSingleItem(int max);
}

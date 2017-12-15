package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.BaseModel;

/**
 * Created by ldockal on 11/25/2017.
 */

public interface SingleItemGenerator<T extends BaseModel> {

    T generateSingleItem();
}

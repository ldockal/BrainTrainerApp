package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.Equation;

/**
 * Created by ldockal on 12/16/2017.
 */

public interface GenerationStrategy {
    public Equation generate(int generatorFactor);
}

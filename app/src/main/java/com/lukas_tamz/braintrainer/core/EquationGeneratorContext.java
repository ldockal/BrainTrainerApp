package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.Equation;

/**
 * Created by ldockal on 12/16/2017.
 */

public class EquationGeneratorContext {
    private GenerationStrategy strategy;

    public EquationGeneratorContext(GenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public Equation executeGenerationForStrategy(int generatorFactor) {
        return strategy.generate(generatorFactor);
    }
}

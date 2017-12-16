package com.lukas_tamz.braintrainer.core;

import com.lukas_tamz.braintrainer.models.Equation;
import com.lukas_tamz.braintrainer.utils.Utils;

/**
 * Created by ldockal on 12/16/2017.
 */

public class MinusEquationGenerator implements GenerationStrategy {
    @Override
    public Equation generate(int generatorFactor) {
        Equation equation = new Equation();
        equation.setOperation('-');

        int num1, num2;

        num1 = Utils.getNumberInRange(1, 10 * generatorFactor);
        num2 = Utils.getNumberInRange(0, 10 * generatorFactor);

        equation.setFirstNumber(num1);
        equation.setLastNumber(num2);

        if (Utils.getNumberInRange(0, 1) == 1) {
            equation.setResult(num1 - num2);
            equation.setCorrect(true);
        } else {
            int res = num1 - num2;
            int incorrectRes = Utils.getNumberInRange(res - 9, res + 9);
            while (res == incorrectRes) {
                incorrectRes = Utils.getNumberInRange(res - 9, res + 9);
            }
            equation.setResult(incorrectRes);
            equation.setCorrect(false);
        }
        return equation;
    }
}

package com.lukas_tamz.braintrainer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 12/16/2017.
 */

public final class Equation implements Serializable {

    private int firstNumber;
    private int lastNumber;
    private char operation;
    private int result;
    private boolean isCorrect;

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return getFirstNumber() == equation.getFirstNumber() &&
                getLastNumber() == equation.getLastNumber() &&
                getOperation() == equation.getOperation() &&
                getResult() == equation.getResult() &&
                isCorrect() == equation.isCorrect();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstNumber(), getLastNumber(), getOperation(), getResult(), isCorrect());
    }

    @Override
    public String toString() {
        return firstNumber + " " + operation + " " + lastNumber + " = " + result;
    }
}

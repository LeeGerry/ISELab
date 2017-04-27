package edu.auburn.iselab.model;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class Moment {
    private double upper;
    private double lower;
    private double value;

    public Moment(double upper, double lower, double value) {
        this.upper = upper;
        this.lower = lower;
        this.value = value;
    }

    public double getUpper() {
        return upper;
    }

    public void setUpper(double upper) {
        this.upper = upper;
    }

    public double getLower() {
        return lower;
    }

    public void setLower(double lower) {
        this.lower = lower;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "upper=" + upper +
                ", lower=" + lower +
                ", value=" + value +
                '}';
    }
}

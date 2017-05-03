package edu.auburn.iselab.model;

import java.io.Serializable;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class Task implements Serializable{
    private int id;
    private double lever;
    private double load;
    private double moment;
    private double multiplier;
    private int repetitions;
    private double damage;
    private double cumLoad;
    private long date;
    public Task(long date, double lever, double load, double moment, double multiplier, int repetitions, double damage, double cumLoad) {
        this.lever = lever;
        this.load = load;
        this.moment = moment;
        this.multiplier = multiplier;
        this.repetitions = repetitions;
        this.damage = damage;
        this.cumLoad = cumLoad;
        this.date = date;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLever() {
        return lever;
    }

    public void setLever(double lever) {
        this.lever = lever;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }

    public double getMoment() {
        return moment;
    }

    public void setMoment(double moment) {
        this.moment = moment;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getCumLoad() {
        return cumLoad;
    }

    public void setCumLoad(double cumLoad) {
        this.cumLoad = cumLoad;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Task{" +
                "lever=" + lever +
                ", load=" + load +
                ", moment=" + moment +
                ", multiplier=" + multiplier +
                ", repetitions=" + repetitions +
                ", damage=" + damage +
                ", cumLoad=" + cumLoad +
                '}';
    }
}

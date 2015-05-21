package pl.javamylove.agricolacalculator.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bartosz Kalina on 21-05-2015.
 */
public class Player implements Serializable {

    private String name;
    private int score;
    private List<Integer> pointsList;

    private int fields = -1;
    private int pasures = -1;
    private int grain = -1;
    private int vegetables = -1;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int score, List<Integer> pointsList) {
        this.name = name;
        this.score = score;
        this.pointsList = pointsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<Integer> pointsList) {
        this.pointsList = pointsList;
    }

    public int getFields() {
        return fields;
    }

    public void setFields(int fields) {
        this.fields = fields;
    }

    public int getPasures() {
        return pasures;
    }

    public void setPasures(int pasures) {
        this.pasures = pasures;
    }

    public int getGrain() {
        return grain;
    }

    public void setGrain(int grain) {
        this.grain = grain;
    }

    public int getVegetables() {
        return vegetables;
    }

    public void setVegetables(int vegetables) {
        this.vegetables = vegetables;
    }
}

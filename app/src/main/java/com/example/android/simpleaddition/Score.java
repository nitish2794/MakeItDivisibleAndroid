package com.example.android.simpleaddition;

/**
 * Created by Nitish on 3/3/2016.
 */
public class Score {


    String name;
    int score;

    public Score() {

    }

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
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
}

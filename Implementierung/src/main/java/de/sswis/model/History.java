package de.sswis.model;


public class History {
    private int[] score;
    private boolean[] cooperated;
    private Agent[] opponent;
    private Strategy[] strategies;

    public History() {

    }

    public int getScore(int round) {
        return score[round];
    }

    public boolean getCooperated(int round) {
        return cooperated[round];
    }

    public Agent getOpponent(int round) {
        return opponent[round];
    }

    public Strategy getStrategy(int round) {
        return strategies[round];
    }

    public void setScore(int score){

    }

    public void setCooperated(boolean cooperated) {

    }

    public void setOpponent(Agent opponent) {

    }

    public void setStrategy(Strategy strategy) {

    }
}

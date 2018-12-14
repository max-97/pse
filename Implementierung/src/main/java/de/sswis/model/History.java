package de.sswis.model;


import java.util.HashMap;

public class History {
    private int[] score;
    private boolean[] cooperated;
    private Agent[] opponent;
    private String[] strategies;
    private HashMap<Agent, Boolean> cooperatedLastTime;
    private HashMap<Agent, Boolean> cooperatedEveryTime;
    private HashMap<Group, Boolean> groupCooperatedLastTime;
    private HashMap<Group, Boolean> groupCooperatedEveryTime;

    public History(int rounds) {

    }

    /*
    public int getScore(int round) {
        return score[round];
    }
    */

    /*
    public boolean getCooperated(int round) {
        return cooperated[round];
    }
    */

    /*
    public Agent getOpponent(int round) {
        return opponent[round];
    }
    */

    /*
    public String getStrategy(int round) {
        return strategies[round];
    }
    */

    /*
    public void setScore(int score){

    }
    */

    /*
    public void setCooperated(boolean cooperated) {

    }
    */

    /*
    public void setOpponent(Agent opponent) {

    }
    */

    /*
    public void setStrategy(Strategy strategy) {

    }
    */

    public boolean cooperatedLastTime(Agent agent) {
        return false;
    }

    public boolean cooperatedEveryTime(Agent agent) {
        return false;
    }

    public boolean groupCooperatedLastTime(Agent agent) {
        return false;
    }

    public boolean groupCooperatedEveryTime(Agent agent) {
        return false;
    }

}

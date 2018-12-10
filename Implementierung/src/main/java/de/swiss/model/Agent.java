package de.swiss.model;


public class Agent {

    private int id;
    private int initialScore;
    private History history;
    private Group group;
    private Strategy strategy;
    private boolean hasMixedStrategy;

    public Agent(int id, int initialScore, Group group, CombinedStrategy initialStrategy) {
    }

    public Agent(int id, int initialScore, Group group, MixedStrategy initialMixedStrategy) {}

    public int getId() {
        return id;
    }

    public int getInitialScore() { return initialScore; }

    public History getHistory() { return history; }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean hasMixedStrategy() {return hasMixedStrategy;}

    public void setCombinedStrategy(CombinedStrategy newStrategy) {

    }
}

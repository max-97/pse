package model;


public class Agent {

    private int id;
    private int initialScore;
    private History history;
    private Group group;
    private CombinedStrategy strategy;

    public Agent(int id, int initialScore, Group group, CombinedStrategy initialStrategy) {
    }

    public int getId() {
        return id;
    }

    public int getInitialScore() { return initialScore; }

    public History getHistory() { return history; }

    public CombinedStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CombinedStrategy newStrategy) {

    }
}

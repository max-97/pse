package de.sswis.view.model;

import java.util.List;

public class VMAgentHistory {

    //TODO: nach Bedarf ergänzen
    private int agentID;
    private int groupID;
    private List<Integer> score;
    private List<Integer> rank;
    private List<String> strategies;

    public VMAgentHistory(int agentID, int groupID, List<Integer> score, List<Integer> rank, List<String> strategies) {
        this.agentID = agentID;
        this.groupID = groupID;
        this.score = score;
        this.rank = rank;
        this.strategies = strategies;
    }

    public int getAgentID() {
        return agentID;
    }

    public int getGroupID() {
        return groupID;
    }

    public List<Integer> getScore() {
        return score;
    }

    public List<Integer> getRank() {
        return rank;
    }

    public List<String> getStrategies() {
        return strategies;
    }
}

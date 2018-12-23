package de.sswis.view.model;

import de.sswis.util.AgentDistribution;

import java.util.List;

public class VMGroup {

    private String name;
    private int id;
    private AgentDistribution agents;

    private List<String> strategies;
    private List<AgentDistribution> strategyDistributions;

    private List<String> startCapital;
    private List<AgentDistribution> startCapitalDistributions;

    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    public void addStrategy (String name, int percentage) {}
    public void addStrategy (String name, int[] ids) {}

    public void addStartCapital (int capital, int percentage) {}
    public void addStartCapital (int capital, int[] ids) {}


}

package de.sswis.model;

import java.util.HashMap;

public class Result {

    private HashMap<Integer, Agent[]> agents;
    private HashMap<Integer, Boolean> equilibriums;

    public Result() {
        this.agents = new HashMap<>();
        this.equilibriums = new HashMap<>();
    }

    public HashMap<Integer, Agent[]> getAgents() {
        return agents;
    }

    public HashMap<Integer, Boolean> getEquilibriums() {
        return equilibriums;
    }

    public void setAgents(int repetition, Agent[] agents) {
        this.agents.put(repetition, agents);
    }

    public void setEquilibrium(int repetition, boolean equilibrium) {
        this.equilibriums.put(repetition, equilibrium);
    }
}

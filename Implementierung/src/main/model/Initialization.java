package model;

public class Initialization {
    private Group[] groups;
    private int agentCount;
    private Agent[] agents;

    public Initialization(Group[] groups, int agentCount, Agent[] agents) {

    }

    public Group[] getGroups () {
        return groups;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public Agent[] getAgents() {
        return agents;
    }

}

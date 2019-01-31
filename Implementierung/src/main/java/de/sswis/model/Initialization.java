package de.sswis.model;

import de.sswis.exceptions.DuplicateObjectNameException;
import de.sswis.util.AgentDistribution;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine Initialisierung welche die in ihr enthaltenen Agenten und Gruppen festlegt.
 * @author Michel Bodé
 */
public class Initialization {

    private String name;
    private List<Group> groups;
    private int agentCount;
    private List<Agent> agents;


    /**
     * Erstellt eine Initialisierung.
     *
     * @param name Name der Initialisierung
     * @param agentCount Anzahl der Agenten
     */
    public Initialization(String name, int agentCount) {
        this.name = name;
        this.agentCount = agentCount;
        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i < agentCount; i++) {
            Agent agent = new Agent(i, 0, null, null);
            agents.add(agent);
        }
        this.agents = agents;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addGroup(int id, String name) {
        for (Group g : groups) {
            if (g.getId() == id || g.getName().equals(name)) {
                throw new DuplicateObjectNameException("this group is already exit.");
            }
        }
        Group group = new Group(id, name);
        groups.add(group);
    }

    /**
     * Setzt die Verteilung der {@code Agenten} für eine {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung der Agenten in einer Gruppe beschreibt
     * @param group die {@code Gruppe} für welche die Verteilung angewandt wird
     */
    public void setGroupDistribution(AgentDistribution distribution, Group group) {
        List<Agent> members = new ArrayList<>();
        if (distribution.usesIDS()){
            int[] ids = distribution.getAgentIDs();
            for (int i = 0; i < distribution.getAgentIDs().length; i++) {
                for (int j = 0; j < agentCount; j++) {
                    if (agents.get(j).getGroup() == null || ids[i] == agents.get(j).getId())
                        agents.get(j).setGroup(group);
                    members.add(agents.get(j));
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < agentCount; x++) {
                if (agents.get(x).getGroup() == null) {
                    agents.get(x).setGroup(group);
                    members.add(agents.get(x));
                    agentNumber++;
                }
                float result = (float) distribution.getPercentage()/100 * (float)agentCount;
                int number = Math.round(result);
                if (agentNumber == number) {
                    break;
                }
            }
        }
        group.setMembers(members);
    }

    /**
     * Setzt die Verteilung der initialen {@code kombinierten Strategien} für die {@code Agenten} in einer
     * {@code Group}.
     *  @param distribution eine {@link AgentDistribution}, welche die Verteilung initialen
     *                      {@code kombinierten Strategien} der Agenten in einer Gruppe beschreibt
     * @param strategy die {@code kombinierten Strategien} für welche die Verteilung angewandt wird
     * @param group die {@code Gruppe} in welcher die Verteilung angewandt wird
     */
    public void setStrategyDistribution(AgentDistribution distribution, Strategy strategy, Group group) {
        List<Agent> members = group.getMembers();
        if (distribution.usesIDS()){
            int[] ids = distribution.getAgentIDs();
            for (int i = 0; i < distribution.getAgentIDs().length; i++) {
                for (int j = 0; j < group.getMembers().size(); j++) {
                    if (ids[i] == members.get(j).getId()) {
                        if (members.get(j).getStrategy() == null) {
                            members.get(j).setStrategy(strategy);
                        }
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < members.size(); x++) {
                if (members.get(x).getStrategy() == null) {
                    members.get(x).setStrategy(strategy);
                    agentNumber++;
                }
                float result = (float) distribution.getPercentage()/100 * (float)members.size();
                int number = Math.round(result);
                if (agentNumber == number) {
                    break;
                }
            }
        }
        group.setMembers(members);
    }

    /**
     * Setzt die Verteilung des initialen Startkapitals der {@code Agenten} in einer {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung des initialen Startkapitals der
     *                     Agenten in einer Gruppe beschreibt
     * @param capital das Startkapital für welches die Verteilung angewandt wird
     * @param group die {@code Gruppe} in welcher die Verteilung angewandt wird
     */
    public void setCapitalDistribution(AgentDistribution distribution, int capital, Group group) {
        List<Agent> members = group.getMembers();
        if (distribution.usesIDS()){
            int[] ids = distribution.getAgentIDs();
            for (int i = 0; i < distribution.getAgentIDs().length; i++) {
                for (int j = 0; j < group.getMembers().size(); j++) {
                    if (ids[i] == members.get(j).getId()) {
                        if (members.get(j).getScore() == 0) {
                            members.get(j).setScore(capital);
                        }
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < members.size(); x++) {
                if (members.get(x).getScore() == 0) {
                    members.get(x).setScore(capital);
                    agentNumber++;
                }
                float result = (float) distribution.getPercentage()/100 * (float)members.size();
                int number = Math.round(result);
                if (agentNumber == number) {
                    break;
                }
            }
        }
        group.setMembers(members);
    }

    public String getName() {
        return name;
    }

    public Group[] getGroups () {
        Group[] newGroups = new Group[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            newGroups[i] = groups.get(i);
        }
        return newGroups;
    }

    public int getAgentCount() {
        return agentCount;
    }

    /**
     * Berechnet die initialen Zustände der Agenten basierend auf den vorher gesetzten Verteilungen.
     *
     * @return ein Array von {@code Agenten} mit den initialen {@code Gruppen}, {@code kombinierten Strategien} und
     * Startkapital
     * @see Initialization#setCapitalDistribution(AgentDistribution, int, Group)
     * @see Initialization#setGroupDistribution(AgentDistribution, Group)
     * @see Initialization#setStrategyDistribution(AgentDistribution, Strategy, Group)
     */
    public Agent[] calculateInitialAgentState() {
        Agent[] newAgents = new Agent[agents.size()];
        for (int i = 0; i < agents.size(); i++) {
            newAgents[i] = agents.get(i);
        }
        return newAgents;
    }

}
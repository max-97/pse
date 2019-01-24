package de.sswis.model;

import de.sswis.util.AgentDistribution;

/**
 * Eine Initialisierung welche die in ihr enthaltenen Agenten und Gruppen festlegt.
 * @author Michel Bodé
 */
public class Initialization {

    private String name;
    private Group[] groups;
    private int agentCount;
    private Agent[] agents;


    /**
     * Erstellt eine Initialisierung.
     *
     * @param name Name der Initialisierung
     * @param agentCount Anzahl der Agenten
     */
    public Initialization(String name, int agentCount) {
        this.name = name;
        this.agentCount = agentCount;
    }

    /**
     * Setzt die Verteilung der {@code Agenten} für eine {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung der Agenten in einer Gruppe beschreibt
     * @param group die {@code Gruppe} für welche die Verteilung angewandt wird
     */
    public void setGroupDistribution(AgentDistribution distribution, Group group) {
        if (distribution.usesIDS()){
            int[] ids = distribution.getIDs();
                for (int i = 0; i < distribution.getIDs().length; i++) {
                    for (int j = 0; j < agents.length; j++) {
                        if (ids[i] == agents[j].getId()) {
                            group.addMember(agents[j]);
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < agents.length; x++) {
                if (agents[x].getGroup().equals(group)) {
                    group.addMember(agents[x]);
                    agentNumber++;
                }
                if ((agentNumber/agents.length) == distribution.getPercentage()) {
                    break;
                }
            }
        }
    }

    /**
     * Setzt die Verteilung der initialen {@code kombinierten Strategien} für die {@code Agenten} in einer
     * {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung initialen
     *                      {@code kombinierten Strategien} der Agenten in einer Gruppe beschreibt
     * @param strategy die {@code kombinierten Strategien} für welche die Verteilung angewandt wird
     * @param group die {@code Gruppe} in welcher die Verteilung angewandt wird
     */
    public void setStrategyDistribution(AgentDistribution distribution, CombinedStrategy strategy, Group group) {
        Agent[] members = group.getMembers();
        if (distribution.usesIDS()){
            int[] ids = distribution.getIDs();
            for (int i = 0; i < distribution.getIDs().length; i++) {
                for (int j = 0; j < group.getMembers().length; j++) {
                    if (ids[i] == members[j].getId()) {
                        members[j]. setStrategy(strategy);
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < members.length; x++) {
                    members[x].setStrategy(strategy);
                    agentNumber++;
                if ((agentNumber/members.length) == distribution.getPercentage()) {
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

    }

    public String getName() {
        return name;
    }

    public Group[] getGroups () {
        return groups;
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
     * @see Initialization#setStrategyDistribution(AgentDistribution, CombinedStrategy, Group)
     */
    public Agent[] calculateInitialAgentState() {
        return agents;
    }

}

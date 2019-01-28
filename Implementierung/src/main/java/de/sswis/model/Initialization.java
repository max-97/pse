package de.sswis.model;

import de.sswis.util.AgentDistribution;

import java.util.List;

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
        List<Agent> members = new ArrayList();
        NumberFormat numberformat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        if (distribution.usesIDS()){
            int[] ids = distribution.getIDs();
                for (int i = 0; i < distribution.getIDs().length; i++) {
                 Agent agent = new Agent(ids[i], 0, group, null);
                 members.add(agent);
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < agentCount; x++) {
                Agent agent = new Agent(x, 0, group, null);
                members.add(agent);
                agentNumber++;
                String result = numberFormat.format((float)agentNumber/(float)agentCount * 100);
                int percent = IInteger.parseInt(result);
                if (percent == distribution.getPercentage()) {
                    break;
                }
            }
        }
        group.setMembers(members);
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
        List<Agent> members = group.getMembers();
        NumberFormat numberformat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        if (distribution.usesIDS()){
            int[] ids = distribution.getIDs();
            for (int i = 0; i < distribution.getIDs().length; i++) {
                for (int j = 0; j < group.getMembers().size(); j++) {
                    if (ids[i] == members.get(j).getId()) {
                        members.get(j).setStrategy(strategy);
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < members.length; x++) {
                members[x].setStrategy(strategy);
                agentNumber++;
                String result = numberFormat.format((float)agentNumber/(float)members.length * 100);
                int percent = IInteger.parseInt(result);
                if (percent == distribution.getPercentage()) {
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
        NumberFormat numberformat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        if (distribution.usesIDS()){
            int[] ids = distribution.getIDs();
            for (int i = 0; i < distribution.getIDs().length; i++) {
                for (int j = 0; j < group.getMembers().size(); j++) {
                    if (ids[i] == members.get(j).getId()) {
                        members.get(j).setScore(capital);
                    }
                }
            }
        } else {
            int agentNumber = 0;
            for (int x = 0; x < members.size(); x++) {
                members.get(x).setScore(capital);
                agentNumber++;
                String result = numberFormat.format((float)agentNumber/(float)members.length * 100);
                int percent = IInteger.parseInt(result);
                if (percent == distribution.getPercentage()) {
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

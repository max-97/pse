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
    }

    /**
     * Setzt die Verteilung der {@code Agenten} für eine {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung der Agenten in einer Gruppe beschreibt
     * @param group die {@code Gruppe} für welche die Verteilung angewandt wird
     */
    public void setGroupDistribution(AgentDistribution distribution, Group group) {

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
     * @see Initialization#setStrategyDistribution(AgentDistribution, Strategy, Group)
     */
    public Agent[] calculateInitialAgentState() {
        return agents;
    }

}

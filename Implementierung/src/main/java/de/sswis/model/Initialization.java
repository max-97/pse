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
    private List<AgentDistribution> groupAgentDistributions;
    private List<AgentDistribution> strategyAgentDistributions;
    private List<AgentDistribution> capitalAgentDistributions;
    private List<Strategy> strategies;
    private List<Integer> capitals;
    private List<Group> groups1;
    private List<Group> groups2;
    private List<Group> groups3;
    private boolean initialScoreStrategiesOnly;

    /**
     * Erstellt eine Initialisierung.
     *
     * @param name Name der Initialisierung
     * @param agentCount Anzahl der Agenten
     */
    public Initialization(String name, int agentCount) {
        this.name = name;
        this.agentCount = agentCount;
        this.agents = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.groupAgentDistributions = new ArrayList<>();
        this.strategyAgentDistributions = new ArrayList<>();
        this.capitalAgentDistributions = new ArrayList<>();
        this.strategies = new ArrayList<>();
        this.capitals = new ArrayList<>();
        this.groups1 = new ArrayList<>();
        this.groups2 = new ArrayList<>();
        this.groups3 = new ArrayList<>();
        for (int i = 0; i < agentCount; i++) {
            Agent agent = new Agent(i, 0, null, null);
            agents.add(agent);
        }
        this.initialScoreStrategiesOnly = false;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addGroup(int id, String name) {
        if (groups.size() != 0) {
        for (Group g : groups) {
            if (g.getId() == id || g.getName().equals(name)) {
                throw new DuplicateObjectNameException("this group is already exit.");
            }
        }
            Group group = new Group(id, name);
            groups.add(group);
        } else {
        	Group group = new Group(id, name);
            groups.add(group);
        }
    }

    /**
     * Setzt die Verteilung der {@code Agenten} für eine {@code Group}.
     *
     * @param distribution eine {@link AgentDistribution}, welche die Verteilung der Agenten in einer Gruppe beschreibt
     * @param group die {@code Gruppe} für welche die Verteilung angewandt wird
     */
    public void setGroupDistribution(AgentDistribution distribution, Group group) {
        this.groupAgentDistributions.add(distribution);
        this.groups1.add(group);
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
        this.strategyAgentDistributions.add(distribution);
        this.strategies.add(strategy);
        this.groups2.add(group);
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
        this.capitalAgentDistributions.add(distribution);
        this.capitals.add(capital);
        this.groups3.add(group);
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

    public void distributeAgent() {
        for (int a = 0; a < groups1.size(); a++) {
            List<Agent> members = ((groups1.get(a).getMembers() == null)? new ArrayList():groups1.get(a).getMembers());
            if (groupAgentDistributions.get(a).usesIDS()) {
                int[] ids = groupAgentDistributions.get(a).getAgentIDs();
                for (int i = 0; i < groupAgentDistributions.get(a).getAgentIDs().length; i++) {
                    for (int j = 0; j < agentCount; j++) {
                        if (agents.get(j).getGroup() == null && ids[i] == agents.get(j).getId()) {
                            agents.get(j).setGroup(groups1.get(a));
                            members.add(agents.get(j));
                        }
                    }
                }
            }
            groups1.get(a).setMembers(members);
        }
        for (int b = 0; b < groups1.size(); b++) {
            List<Agent> members = ((groups1.get(b).getMembers() == null)? new ArrayList():groups1.get(b).getMembers());
            if (!groupAgentDistributions.get(b).usesIDS()) {
                int agentNumber = members.size();
                for (int x = 0; x < agentCount; x++) {
                    if (agents.get(x).getGroup() == null) {
                        agents.get(x).setGroup(groups1.get(b));
                        members.add(agents.get(x));
                        agentNumber++;
                    }
                    float result = (float) groupAgentDistributions.get(b).getPercentage()/100 * (float)agentCount;
                    int number = Math.round(result);
                    int allAgent = 0;
                    for (int z = 0; z < groups.size(); z++) {
                        allAgent = allAgent + groups.get(z).getMembers().size();
                    }
                    if (agentNumber == number || allAgent == agentCount) {
                        break;
                    }
                }
            }
            groups1.get(b).setMembers(members);
        }
    }

    public void distributeStrategy() {
        for (int a = 0; a < groups2.size(); a++) {
            List<Agent> members = groups2.get(a).getMembers();
            if (strategyAgentDistributions.get(a).usesIDS()) {
                int[] ids = strategyAgentDistributions.get(a).getAgentIDs();
                for (int i = 0; i < strategyAgentDistributions.get(a).getAgentIDs().length; i++) {
                    for (int j = 0; j < members.size(); j++) {
                        if (ids[i] == members.get(j).getId() && members.get(j).getStrategy() == null) {
                            members.get(j).setStrategy(strategies.get(a).clone());
                        }
                    }
                }
            }
        }
        for (int b = 0; b< groups2.size(); b++) {
            List<Agent> members = groups2.get(b).getMembers();
            if (!strategyAgentDistributions.get(b).usesIDS()) {
                int agentNumber = 0;
                for (int y = 0; y < members.size(); y++) {
                    if (members.get(y).getStrategy() == strategies.get(b)) {
                        agentNumber++;
                    }
                }
                for (int x = 0; x < members.size(); x++) {
                    boolean isAll = true;
                    if (members.get(x).getStrategy() == null) {
                        members.get(x).setStrategy(strategies.get(b).clone());
                        agentNumber++;
                    }
                    float result = (float) strategyAgentDistributions.get(b).getPercentage() / 100 * (float) members.size();
                    int number = Math.round(result);
                    for (int z = 0; z < members.size(); z++) {
                        if (members.get(z).getStrategy() == null) {
                            isAll = false;
                        }
                    }
                    if (agentNumber == number || isAll) {
                        break;
                    }
                }
            }
        }
    }

    public void distributeCapital() {
        for (int a = 0; a < groups3.size(); a++) {
            List<Agent> members = groups3.get(a).getMembers();
            if (capitalAgentDistributions.get(a).usesIDS()) {
                int[] ids = capitalAgentDistributions.get(a).getAgentIDs();
                for (int i = 0; i < capitalAgentDistributions.get(a).getAgentIDs().length; i++) {
                    for (int j = 0; j < members.size(); j++) {
                        if (ids[i] == members.get(j).getId() && members.get(j).getScore() == 0) {
                            members.get(j).setScore(capitals.get(a));
                        }
                    }
                }
            }
        }
        for (int b = 0; b < groups3.size(); b++) {
            List<Agent> members = groups3.get(b).getMembers();
            if (!capitalAgentDistributions.get(b).usesIDS()) {
                int agentNumber = 0;
                for (int y = 0; y < members.size(); y++) {
                    if (members.get(y).getScore() == capitals.get(b)) {
                        agentNumber++;
                    }
                }
                for (int x = 0; x < members.size(); x++) {
                    boolean isAll = true;
                    if (members.get(x).getScore() == 0) {
                        members.get(x).setScore(capitals.get(b));
                        agentNumber++;
                    }
                    float result = (float) capitalAgentDistributions.get(b).getPercentage() / 100 * (float) members.size();
                    int number = Math.round(result);
                    for (int z = 0; z < members.size(); z++) {
                        if (members.get(z).getScore() == 0) {
                            isAll = false;
                        }
                    }
                    if (agentNumber == number || isAll) {
                        break;
                    }
                }
            }
        }
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
        distributeAgent();
        distributeCapital();
        distributeStrategy();
        Agent[] newAgents = new Agent[agents.size()];
        for (int i = 0; i < agents.size(); i++) {
            newAgents[i] = agents.get(i);
        }
        return newAgents;
    }

    public boolean getInitialScoreStrategiesOnly() {
        return initialScoreStrategiesOnly;
    }

    public void setInitialScoreStrategiesOnly(boolean initialScoreStrategiesOnly) {
        this.initialScoreStrategiesOnly = initialScoreStrategiesOnly;
    }

}
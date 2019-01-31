package de.sswis.view.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Gruppen Daten, die alle nötigen Parameter zum Erzeugen einer {@code Group} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMGroup {

    private String name;
    private int id;
    private List<String> agentIntervals;

    private List<String> strategies = new ArrayList<>();
    private List<List<String>> strategyDistributions = new ArrayList<>();
    private boolean relativeStrategyDistribution;

    private List<String> startCapital = new ArrayList<>();
    private List<List<String>> startCapitalDistributions = new ArrayList<>();
    private boolean relativeCapitalDistribution;


    /**
     * Gibt eine String der wichtige Informationen zu dieser Gruppe zusammenfasst.
     * @return String enthält Kurzbeschreibung der Gruppe
     */
    public String getToolTipText() {    return ""; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAgents() { return agentIntervals; }

    public void setAgents(String agentIntervals) {
        this.agentIntervals = distributionList(agentIntervals);
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public List<List<String>> getStrategyDistributions() {
        return strategyDistributions;
    }

    public void addStrategy(String name, String distribution) {
        this.strategies.add(name);
        this.strategyDistributions.add(distributionList(distribution));
    }

    public void addStrategyList(List<String> names, List<String> distributions) {
        //names.size() == distributions.size()
        Iterator<String> it1 = names.iterator();
        Iterator<String> it2 = distributions.iterator();

        while (it1.hasNext()) { // == it2.hasNext()
            addStrategy(it1.next(), it2.next());
        }
    }

    public void setStrategyList(List<String> names, List<String> distributions) {
        //names.size() == distributions.size()
        this.strategies = new ArrayList<>(names.size());
        this.strategyDistributions = new ArrayList<>(names.size());
        addStrategyList(names, distributions);
    }

    public List<String> getStartCapital() {
        return startCapital;
    }

    public List<List<String>> getStartCapitalDistributions() {
        return startCapitalDistributions;
    }

    public void addStartCapital(String capital, String distribution) {
        this.startCapital.add(capital);
        this.startCapitalDistributions.add(distributionList(distribution));
    }

    public void addStartCapitalList(List<String> capital, List<String> distributions) {
        //capital.size() == distributions.size()
        Iterator<String> it1 = capital.iterator();
        Iterator<String> it2 = distributions.iterator();

        while (it1.hasNext()) { // == it2.hasNext()
            addStrategy(it1.next(), it2.next());
        }
    }

    public void setStartCapitalList(List<String> capital, List<String> distributions) {
        this.startCapital = new ArrayList<>(capital.size());
        this.startCapitalDistributions = new ArrayList<>(capital.size());
        addStartCapitalList(capital, distributions);
    }

    public boolean getRelativeStrategyDistributions() {
        return relativeStrategyDistribution;
    }

    public void setRelativeStrategyDistribution(boolean relativeStrategyDistribution) {
        this.relativeStrategyDistribution = relativeStrategyDistribution;
    }

    public boolean getRelativeCapitalDistributions() {
        return relativeCapitalDistribution;
    }

    public void setRelativeCapitalDistributions(boolean relativeCapitalDistributions) {
        this.relativeCapitalDistribution = relativeCapitalDistributions;
    }

    public boolean hasMultiComponent() {

        for (String str1 : this.agentIntervals) {
            if (isMultiString(str1)) return true;
        }
        for (List<String> list2 : this.strategyDistributions) {
            for (String str2 : list2) {
                if (isMultiString(str2)) return true;
            }
        }
        for (List<String> list3 : this.startCapitalDistributions) {
            for (String str3 : list3) {
                if (isMultiString(str3)) return true;
            }
        }
        return false;
    }

    private List<String> distributionList(String distribution) {
        String[] parts = distribution.trim().split(",");
        return new ArrayList<>(Arrays.asList(parts));
    }

    private boolean isMultiString(String str) {
        return str.matches(".*(-.*){2}.*");
    }
}

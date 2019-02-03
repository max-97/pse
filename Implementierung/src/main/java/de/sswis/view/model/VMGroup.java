package de.sswis.view.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static de.sswis.util.InputValidator.containsFamilyOfValues;
import static de.sswis.util.InputValidator.isFamilyOfPercentages;

/**
 * Gruppen Daten, die alle nötigen Parameter zum Erzeugen einer {@code Group} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMGroup {

    private String name;
    private int id;
    private String agentIntervals;

    private List<String> strategies = new ArrayList<>();
    private List<String> strategyDistributions = new ArrayList<>();
    private boolean relativeStrategyDistribution = false;

    private List<String> startCapital = new ArrayList<>();
    private List<String> startCapitalDistributions = new ArrayList<>();
    private boolean relativeCapitalDistribution = false;


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

    public List<String> getAgents() { return distributionList(this.agentIntervals); }

    public String getAgentsString() {
        return this.agentIntervals;
    }

    public void setAgents(String agentIntervals) {
        this.agentIntervals = agentIntervals;
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public List<List<String>> getStrategyDistributions() {
        List<List<String>> output = new ArrayList<>(this.strategyDistributions.size());
        for (String str : strategyDistributions) {
            output.add(distributionList(str));
        }
        return output;
    }

    public List<String> getStrategyDistributionsStrings() { return this.strategyDistributions; }

    public void addStrategy(String name, String distribution) {
        this.strategies.add(name);
        this.strategyDistributions.add(distribution);
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
        List<List<String>> output = new ArrayList<>(this.startCapitalDistributions.size());
        for (String str : this.startCapitalDistributions) {
            output.add(distributionList(str));
        }
        return output;
    }

    public void addStartCapital(String capital, String distribution) {
        this.startCapital.add(capital);
        this.startCapitalDistributions.add(distribution);
    }

    public void addStartCapitalList(List<String> capital, List<String> distributions) {
        //capital.size() == distributions.size()
        Iterator<String> it1 = capital.iterator();
        Iterator<String> it2 = distributions.iterator();

        while (it1.hasNext()) { // == it2.hasNext()
            addStartCapital(it1.next(), it2.next());
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

        if (containsFamilyOfValues(this.agentIntervals) || isFamilyOfPercentages(this.agentIntervals)) return true;

        for (String stratDistr : this.strategyDistributions) {
            if (containsFamilyOfValues(stratDistr) || isFamilyOfPercentages(stratDistr)) return true;
        }

        for (String startCapDistr : this.startCapitalDistributions) {
            if (containsFamilyOfValues(startCapDistr) || isFamilyOfPercentages(startCapDistr)) return true;
        }
        return false;
    }

    private List<String> distributionList(String distribution) {
        String[] parts = distribution.trim().split(",");
        return new ArrayList<>(Arrays.asList(parts));
    }
}

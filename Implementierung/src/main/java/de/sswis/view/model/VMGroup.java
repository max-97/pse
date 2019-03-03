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
    private String agents;

    private List<String> strategies = new ArrayList<>();
    private List<String> strategyDistributions = new ArrayList<>();
    private boolean relativeStrategyDistribution = false;

    private List<String> startCapitals = new ArrayList<>();
    private List<String> startCapitalDistributions = new ArrayList<>();
    private boolean relativeCapitalDistribution = false;

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

    public String getAgentsString() {
        return this.agents;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public List<String> getStrategyDistributionsStrings() { return this.strategyDistributions; }

    public List<String> getStartCapitalDistributionsStrings() { return this.startCapitalDistributions; }


    public void addStrategy(String name, String distribution) {
        this.strategies.add(name);
        this.strategyDistributions.add(distribution);
    }

    public List<String> getStartCapital() {
        return startCapitals;
    }


    public void addStartCapital(String capital, String distribution) {
        this.startCapitals.add(capital);
        this.startCapitalDistributions.add(distribution);
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
        return hasVariableCapital() || hasVariableCapitalDistribution() || hasVariableStrategyDistribution();
    }

    public boolean hasVariableStrategyDistribution() {
        if(!relativeStrategyDistribution) {
            return false;
        } else {
            for(String distribution : strategyDistributions) {
                if(distribution.matches("\\d+ - \\d+ - \\d+")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasVariableCapitalDistribution() {
        if(!relativeCapitalDistribution) {
            return false;
        } else {
            for(String distribution : startCapitalDistributions) {
                if(distribution.matches("\\d+ - \\d+ - \\d+")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasVariableCapital() {
        for(String distribution : startCapitals) {
            if(distribution.matches("\\d+ - \\d+ - \\d+")) {
                return true;
            }
        }
        return false;
    }
}

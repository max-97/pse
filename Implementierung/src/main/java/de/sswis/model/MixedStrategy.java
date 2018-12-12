package de.sswis.model;

public class MixedStrategy implements Strategy{

    private String name;
    private String description;
    private CombinedStrategy[] combinedStrategies;
    private double[] probabilities;

    public MixedStrategy(String name, String description, CombinedStrategy[] combinedStrategies, double[] probabilities) {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }

    public double[] getProbabilities() {
        return probabilities;
    }
}

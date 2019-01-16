package de.sswis.model;

/**
 * Eine gemischte Strategie bestehend aus Paaren von kombinierten Strategien und Wahrscheinlichkeiten.
 */
public class MixedStrategy implements Strategy {

    private String name;
    private CombinedStrategy[] combinedStrategies;
    private double[] probabilies;

    /**
     * Erstellt eine gemischte Strategie.
     * @param name Name der Strategie
     * @param combinedStrategies Menge an kombinierten Strategien
     * @param probabilies Menge an Wahrscheinlichkeiten
     */
    public MixedStrategy(String name, CombinedStrategy[] combinedStrategies, double[] probabilies) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }

    public double[] getProbabilities() {
        return probabilies;
    }

    public CombinedStrategy[] getCombinedStrategies() {
        return combinedStrategies;
    }
}

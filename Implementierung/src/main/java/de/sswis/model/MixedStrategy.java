package de.sswis.model;

/**
 * Eine gemischte Strategie bestehend aus Paaren von kombinierten Strategien und Wahrscheinlichkeiten.
 */
public class MixedStrategy implements Strategy {

    private String name;
    private CombinedStrategy[] combinedStrategies;
    private double[] probabilities;
    private int adaptationCount;

    /**
     * Erstellt eine gemischte Strategie.
     * @param name Name der Strategie
     * @param combinedStrategies Menge an kombinierten Strategien
     * @param probabilities Menge an Wahrscheinlichkeiten
     */
    public MixedStrategy(String name, CombinedStrategy[] combinedStrategies, double[] probabilities) {
        this.name = name;
        this.combinedStrategies = combinedStrategies;
        this.probabilities = probabilities;
        this.adaptationCount = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        double random = Math.random();
        double sum = 0;
        for(int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
            if(random < sum || i == probabilities.length - 1) {
                return combinedStrategies[i].calculateAction(agent1, agent2);
            }
        }
        return null;
    }

    public double[] getProbabilities() {
        return probabilities;
    }

    public CombinedStrategy[] getCombinedStrategies() {
        return combinedStrategies;
    }

    public MixedStrategy clone() {
        MixedStrategy strategy = new MixedStrategy(this.name, this.combinedStrategies.clone(), this.probabilities.clone());
        strategy.setAdaptationCount(adaptationCount);
        return strategy;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getAdaptationCount() {
        return adaptationCount;
    }

    public void setAdaptationCount(int count) {
        this.adaptationCount = count;
    }
}

package de.sswis.model;

/**
 * Eine Strategie bestehend aus Paaren von kombinierten Strategien und Wahrscheinlichkeiten.
 * Eine Strategie kann sowohl eine einfache als auch eine gemischte Strategie darstellen.
 * @author Michel Bodé
 */
public class Strategy {
    private String name;
    private CombinedStrategy[] combinedStrategies;
    private double[] probabilities;

    /**
     * Erstellt eine Strategie.
     * @param name Name der Strategie
     * @param combinedStrategies Menge an kombinierten Strategien
     * @param probabilities Menge an Wahrscheinlichkeiten
     */
    public Strategy(String name, CombinedStrategy[] combinedStrategies, double[] probabilities) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /**
     * Berechnet die Aktion des Agenten um dessen Strategie es sich handelt im Spiel mit einem zweiten Agenten.
     * @param agent1 Agent
     * @param agent2 Gegenspieler
     * @return die Aktion des Agenten
     */
    public  Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }

}

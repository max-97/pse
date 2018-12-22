package de.sswis.model;

import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.List;

/**
 * Eine Konfiguration welche alle Parameter enthält die zur Erzeugung einer {@code Simulation} nötig sind.
 * Sie besteht im Wesentlichen aus einer Initialisierung, einem Stufenspiel und den benötigten Algorithmen.
 * @author Michel Bodé
 */
public class Configuration {
    private Game game;
    private Initialization init;
    private AdaptationAlgorithm adaptationAlg;
    private PairingAlgorithm pairingAlg;
    private RankingAlgorithm rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private double adaptationProbability;
    private List<Strategy> strategies;

    /**
     * Erstellt eine Konfiguration.
     * @param game Stufenspiel
     * @param adaptation Anpassungsalgorithmus
     * @param pairing Paarungsalgorithmus
     * @param ranking Bewertungsalgorithmus
     * @param rounds Rundenanzahl
     * @param cycles Zyklenanzahl
     * @param adaptationProbability Wahrscheinlichkeit für die Anpassung der Strategien am Ende jedes Zyklus
     * @param strategies Menge an möglichen Strategien
     */
    public Configuration(Game game, AdaptationAlgorithm adaptation, PairingAlgorithm pairing, RankingAlgorithm ranking,
                         int rounds, int cycles, double adaptationProbability, List<Strategy> strategies) {

    }

    public Game getGame() {
        return game;
    }

    public Initialization getInit(){
        return init;
    }

    public AdaptationAlgorithm getAdaptationAlg(){
        return adaptationAlg;
    }

    public PairingAlgorithm getPairingAlg() {
        return pairingAlg;
    }

    public int getRounds() {
        return rounds;
    }

    public int getCycles() {
        return cycles;
    }

    public int getCycleRoundCount() {
        return cycleRoundCount;
    }

    public double getAdaptationProbability() {
        return adaptationProbability;
    }

    /**
     * Gibt eine {@code Simulation} entsprechend dieser Konfiguration zurück.
     * @return erzeugte Simulation
     */
    public Simulation simulate() {
        return new Simulation(this);
    }

    /**
     * Gibt die möglichen Strategien dieser Konfiguration zurück.
     * @return Menge an Strategien
     */
    public List<Strategy> getPossibleStrategies() {
        return strategies;
    }

    /**
     * Fügt eine Strategie zu den möglichen Strategien hinzu.
     * @param newStrategy hinzuzufügende Strategie
     */
    public void addStrategy(Strategy newStrategy) {

    }

}

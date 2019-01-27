package de.sswis.model;

import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.List;

/**
 * Eine Konfiguration welche alle Parameter enthaelt die zur Erzeugung einer {@code Simulation} noetig sind.
 * Sie besteht im Wesentlichen aus einer Initialisierung, einem Stufenspiel und den benoetigten Algorithmen.
 * @author Michel Bodé
 */
public class Configuration {

    private String name;
    private Game game;
    private Initialization init;
    private AdaptationAlgorithm adaptationAlg;
    private PairingAlgorithm pairingAlg;
    private RankingAlgorithm rankingAlg;
    private int rounds;
    private int cycles;
    private int cycleRoundCount;
    private double adaptationProbability;
    private Simulation simulation;

    /**
     * Erstellt eine Konfiguration.
     * @param name Name
     * @param game Stufenspiel
     * @param adaptation Anpassungsalgorithmus
     * @param pairing Paarungsalgorithmus
     * @param ranking Bewertungsalgorithmus
     * @param rounds Rundenanzahl
     * @param cycles Zyklenanzahl
     * @param adaptationProbability Wahrscheinlichkeit fuer die Anpassung der Strategien am Ende jedes Zyklus
     */
    public Configuration(String name, Game game, AdaptationAlgorithm adaptation, PairingAlgorithm pairing, RankingAlgorithm ranking,
                         int rounds, int cycles, double adaptationProbability) {
        this.name = name;
        this.game = game;
        this.adaptationAlg = adaptation;
        this.pairingAlg = pairing;
        this.rankingAlg = ranking;
        this.rounds = rounds;
        this.cycles = cycles;
        this.adaptationProbability = adaptationProbability;
}

    public String getName() {
        return name;
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
     * Gibt eine {@code Simulation} entsprechend dieser Konfiguration zurueck.
     * @return erzeugte Simulation
     */
    public Simulation simulate() {
        this.simulation = new Simulation(this);
        return this.simulation;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }




}

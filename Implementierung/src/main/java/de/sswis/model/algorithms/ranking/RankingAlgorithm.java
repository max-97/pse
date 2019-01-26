package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Ein Algorithmus zum Bewerten der Agenten einer Simulation.
 * @author Michel Bodé
 */
public interface RankingAlgorithm {
    /**
     * Bewertet die Agenten entsprechend des Algorithmus.
     * @param agents Agenten die bewertet werden sollen
     * @return eine {@code HashMap} die jedem Agenten seinen Rang zuordnet
     */
    HashMap<Agent, Integer> getRankings(Agent[] agents);

    String getName();
}

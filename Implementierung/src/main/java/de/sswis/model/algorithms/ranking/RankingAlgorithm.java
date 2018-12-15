package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;
import de.sswis.model.Simulation;

import java.util.HashMap;

/**
 * Ein Algorithmus zum Bewerten der Agenten einer Simulation.
 * @author Michel Bodé
 */
public interface RankingAlgorithm {
    /**
     * Bewertet die Agenten entsprechend des Algorithmus.
     * @param sim die Simulation deren Agenten bewertet werden sollen
     * @return eine {@code HashMap} die jedem Agenten seinen Rang zuordnet
     */
    HashMap<Agent, Integer> getRankings(Simulation sim);
}

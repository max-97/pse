package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;


import java.util.HashMap;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer Punktzahl in den bisherigen
 * {@code WINDOW_SIZE} Zyklen bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class CustomCycleScore implements RankingAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};
    private final int WINDOW_SIZE;

    /**
     * Konstruktor
     * @param WINDOW_SIZE Anzahl der zu betrachtenden Zyklen
     */
    public CustomCycleScore(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        return null;
    }
}

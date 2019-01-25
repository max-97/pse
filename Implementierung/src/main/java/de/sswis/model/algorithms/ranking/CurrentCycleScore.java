package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer Punktzahl im aktuellen Zyklus bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class CurrentCycleScore implements RankingAlgorithm {

    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}

package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.HashMap;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihres Durchschnittsranges ueber die letzten
 * Zyklen bewertet. Fuer jeden bisherigen Zyklus wird die Gesamtpunktzahl der vorherigen {@code WINDOW_SIZE} Zyklen
 * bestimmt und die Agenten erhalten entsprechend dieser Punktzahl einen Rang. Der Durchschnitt dieser Raenge
 * ueber alle bisherigen Zyklen ist der finale Rang.
 * @author Michel Bodé
 */
public class AverageRank implements RankingAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};
    private final int WINDOW_SIZE;

    /**
     * Konstruktor
     * @param WINDOW_SIZE Anzahl der zu betrachtenden Zyklen
     */
    public AverageRank(int WINDOW_SIZE) {
        this.WINDOW_SIZE = WINDOW_SIZE;
    }

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}

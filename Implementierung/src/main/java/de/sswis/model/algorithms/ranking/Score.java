package de.sswis.model.algorithms.ranking;

import de.sswis.model.Agent;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Ein Algorithmus der die Agenten einer Simulation entsprechend ihrer bisherigen Gesamtpunktzahl bewertet.
 * Hat ein Agent eine hoehere Punktzahl als ein anderer, so ist auch sein Rang hoeher.
 * @author Michel Bodé
 */
public class Score implements  RankingAlgorithm {
    public static final String NAME = "";
    public static final String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public HashMap<Agent, Integer> getRankings(Agent[] agents) {
        TreeMap<Integer, Agent> map = new TreeMap<>();
        HashMap<Agent, Integer> result = new HashMap<>();

        for(int i = 0; i < agents.length; i++) {
            map.put(agents[i].getHistory().getScore(), agents[i]);
        }

        int count = agents.length;
        for(Map.Entry<Integer, Agent> entry : map.entrySet()) {
            result.put(entry.getValue(), count--);
        }

        return result;
    }
}

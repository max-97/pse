package de.sswis.controller;


import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Ein Dienstleister zur Bereitstellung von Listen mit allen Ausprägungen eines bestimmten Algorithmentyps sowie von Bedingungen und Basis-Strategien.
 * Die Algorithmentypen umfassen Adaptionsalgorithmen, Paarungsalgorithmen und Bewertungsalgorithmen.
 *
 * @author Simon Hügel
 */
public class ModelServiceLoader {

    private Collection<AdaptationAlgorithm> adaptAlgorithms;
    private Collection<PairingAlgorithm> pairAlgorithms;
    private Collection<RankingAlgorithm> rankAlgorithms;
    private Collection<Condition> conditions;
    private Collection<BaseStrategy> baseStrategies;

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code AdaptationAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Adaptionsalgorithmen
     */
    public Collection<AdaptationAlgorithm> getAdaptAlgorithmList() {
        if (adaptAlgorithms.isEmpty()) {
            adaptAlgorithms = new ArrayList<>();
            for (AdaptationAlgorithm aa : ServiceLoader.load(AdaptationAlgorithm.class)) {
                adaptAlgorithms.add(aa);
            }
        }
        return adaptAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code PairingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Paarungsalgorithmen
     */
    public Collection<PairingAlgorithm> getPairAlgorithmList() {
        if (pairAlgorithms.isEmpty()) {
            pairAlgorithms = new ArrayList<>();
            for (PairingAlgorithm pa : ServiceLoader.load(PairingAlgorithm.class)) {
                pairAlgorithms.add(pa);
            }
        }
        return pairAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code RankingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Bewertungsalgorithmen
     */
    public Collection<RankingAlgorithm> getRankAlgorithmList() {
        if (rankAlgorithms.isEmpty()) {
            rankAlgorithms = new ArrayList<>();
            for (RankingAlgorithm ra : ServiceLoader.load(RankingAlgorithm.class)) {
                rankAlgorithms.add(ra);
            }
        }
        return rankAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Bedingungen, die das {@code Condition}-Interface implementieren.
     *
     * @return eine Liste mit allen Bedingungen
     */
    public Collection<Condition> getConditionList() {
        if (conditions.isEmpty()) {
            conditions = new ArrayList<>();
            for (Condition c : ServiceLoader.load(Condition.class)) {
                conditions.add(c);
            }
        }
        return conditions;
    }

    /**
     * Liefert eine Liste mit allen Strategien, die das {@code BaseStrategy}-Interface implementieren.
     *
     * @return eine Liste mit allen Basis-Strategien
     */
    public Collection<BaseStrategy> getBaseStrategyList() {
        if (baseStrategies.isEmpty()) {
            baseStrategies = new ArrayList<>();
            for (BaseStrategy bs : ServiceLoader.load(BaseStrategy.class)) {
                baseStrategies.add(bs);
            }
        }
        return baseStrategies;
    }

}



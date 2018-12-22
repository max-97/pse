package de.sswis.controller;


import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Ein Dienstleister zur Bereitstellung von Listen mit allen Ausprägungen eines bestimmten Algorithmentyps sowie von Bedingungen und Basis-Strategien.
 * Die Algorithmentypen umfassen Adaptionsalgorithmen, Paarungsalgorithmen und Bewertungsalgorithmen.
 *
 * @author Simon Hügel
 */
public class ModelServiceLoader {

    private ServiceLoader<AdaptationAlgorithm> adaptAlgLoader = ServiceLoader.load(AdaptationAlgorithm.class);
    private ServiceLoader<PairingAlgorithm> pairAlgLoader = ServiceLoader.load(PairingAlgorithm.class);
    private ServiceLoader<RankingAlgorithm> rankAlgLoader = ServiceLoader.load(RankingAlgorithm.class);
    private ServiceLoader<Condition> conditionLoader = ServiceLoader.load(Condition.class);
    private ServiceLoader<BaseStrategy> baseStrategyLoader = ServiceLoader.load(BaseStrategy.class);
    private List<AdaptationAlgorithm> adaptAlgorithms = null;
    private List<PairingAlgorithm> pairAlgorithms = null;
    private List<RankingAlgorithm> rankAlgorithms = null;
    private List<Condition> conditions = null;
    private List<BaseStrategy> baseStrategies = null;

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code AdaptationAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Adaptionsalgorithmen
     */
    public List<AdaptationAlgorithm> getAdaptAlgorithmList() {
        /*if (adaptAlgorithms == null) {
            for (AdaptationAlgorithm aa : this.adaptAlgLoader) {
                adaptAlgorithms.add(aa);
            }
        }*/
        return adaptAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code PairingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Paarungsalgorithmen
     */
    public List<PairingAlgorithm> getPairAlgorithmList() {
        /*if (pairAlgorithms == null) {
            for (PairingAlgorithm pa : this.pairAlgLoader) {
                pairAlgorithms.add(pa);
            }
        }*/
        return pairAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code RankingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Bewertungsalgorithmen
     */
    public List<RankingAlgorithm> getRankAlgorithmList() {
        /*if (rankAlgorithms == null) {
            for (RankingAlgorithm ra : this.rankAlgLoader) {
                rankAlgorithms.add(ra);
            }
        }*/
        return rankAlgorithms;
    }

    /**
     * Liefert eine Liste mit allen Bedingungen, die das {@code Condition}-Interface implementieren.
     *
     * @return eine Liste mit allen Bedingungen
     */
    public List<Condition> getConditionList() {
        /*if (conditions == null) {
            for (Condition c : this.conditionLoader) {
                conditions.add(c);
            }
        }*/
        return conditions;
    }

    /**
     * Liefert eine Liste mit allen Strategien, die das {@code BaseStrategy}-Interface implementieren.
     *
     * @return eine Liste mit allen Basis-Strategien
     */
    public List<BaseStrategy> getBaseStrategyList() {
        /*if (baseStrategies == null) {
            for (BaseStrategy bs : this.baseStrategyLoader) {
                baseStrategies.add(bs);
            }
        }*/
        return baseStrategies;
    }

}



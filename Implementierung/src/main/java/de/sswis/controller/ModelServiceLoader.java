package de.sswis.controller;


import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

/**
 * Ein Dienstleister zur Bereitstellung von Listen mit allen Ausprägungen eines bestimmten Algorithmentyps sowie von Bedingungen und Basis-Strategien.
 * Die Algorithmentypen umfassen Adaptionsalgorithmen, Paarungsalgorithmen und Bewertungsalgorithmen.
 *
 * @author Simon Hügel
 */
public class ModelServiceLoader {

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code AdaptationAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Adaptionsalgorithmen
     */
    public Collection<AdaptationAlgorithm> getAdaptAlgorithmList() {
        ArrayList<AdaptationAlgorithm> list = new ArrayList<>();
        ServiceLoader.load(AdaptationAlgorithm.class).iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code PairingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Paarungsalgorithmen
     */
    public Collection<PairingAlgorithm> getPairAlgorithmList() {
        ArrayList<PairingAlgorithm> list = new ArrayList<>();
        ServiceLoader.load(PairingAlgorithm.class).iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Liefert eine Liste mit allen Algorithmen, die das {@code RankingAlgorithm}-Interface implementieren.
     *
     * @return eine Liste mit allen Bewertungsalgorithmen
     */
    public Collection<RankingAlgorithm> getRankAlgorithmList() {
        ArrayList<RankingAlgorithm> list = new ArrayList<>();
        ServiceLoader.load(RankingAlgorithm.class).iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Liefert eine Liste mit allen Bedingungen, die das {@code Condition}-Interface implementieren.
     *
     * @return eine Liste mit allen Bedingungen
     */
    public Collection<Condition> getConditionList() {
        ArrayList<Condition> list = new ArrayList<>();
        ServiceLoader.load(Condition.class).iterator().forEachRemaining(list::add);
        return list;
    }

    /**
     * Liefert eine Liste mit allen Strategien, die das {@code BaseStrategy}-Interface implementieren.
     *
     * @return eine Liste mit allen Basis-Strategien
     */
    public Collection<BaseStrategy> getBaseStrategyList() {
        ArrayList<BaseStrategy> list = new ArrayList<>();
        ServiceLoader.load(BaseStrategy.class).iterator().forEachRemaining(list::add);
        return list;
    }

}



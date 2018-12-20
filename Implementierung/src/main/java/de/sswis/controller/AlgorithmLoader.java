package de.sswis.controller;


import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.List;
import java.util.ServiceLoader;

/**
 * Ein Dienstleister zur Bereitstellung von Listen mit allen Ausprägungen eines bestimmten Algorithmentyps.
 * Algorithmentypen umfassen Adaptionsalgorithmen, Paarungsalgorithmen und Bewertungsalgorithmen.
 *
 * @author Simon Hügel
 */
public class AlgorithmLoader {

    private ServiceLoader<AdaptationAlgorithm> adaptAlgLoader = ServiceLoader.load(AdaptationAlgorithm.class);
    private ServiceLoader<PairingAlgorithm> pairAlgLoader = ServiceLoader.load(PairingAlgorithm.class);
    private ServiceLoader<RankingAlgorithm> rankAlgLoader = ServiceLoader.load(RankingAlgorithm.class);
    private List<AdaptationAlgorithm> adaptAlgorithms = null;
    private List<PairingAlgorithm> pairAlgorithms = null;
    private List<RankingAlgorithm> rankAlgorithms = null;

    public List<AdaptationAlgorithm> getAdaptAlgorithms() {
        if (adaptAlgorithms == null) fillAdaptAlgorithmsList(adaptAlgLoader);
        return adaptAlgorithms;
    }

    public List<PairingAlgorithm> getPairAlgorithms() {
        if (pairAlgorithms == null) fillPairAlgorithmsList(pairAlgLoader);
        return pairAlgorithms;
    }

    public List<RankingAlgorithm> getRankAlgorithms() {
        if (rankAlgorithms == null) fillRankAlgorithmsList(rankAlgLoader);
        return rankAlgorithms;
    }

    private void fillAdaptAlgorithmsList(ServiceLoader<AdaptationAlgorithm> adaptAlgLoader) {
        for (AdaptationAlgorithm aa : adaptAlgLoader) {
            adaptAlgorithms.add(aa);
        }
    }

    private void fillPairAlgorithmsList(ServiceLoader<PairingAlgorithm> pairAlgLoader) {
        for (PairingAlgorithm pa : pairAlgLoader) {
            pairAlgorithms.add(pa);
        }
    }

    private void fillRankAlgorithmsList(ServiceLoader<RankingAlgorithm> rankAlgLoader) {
        for (RankingAlgorithm ra : rankAlgLoader) {
            rankAlgorithms.add(ra);
        }
    }

}



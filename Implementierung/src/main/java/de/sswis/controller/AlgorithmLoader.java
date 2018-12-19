package de.sswis.controller;


import de.sswis.model.algorithms.adaptation.AdaptationAlgorithm;
import de.sswis.model.algorithms.pairing.PairingAlgorithm;
import de.sswis.model.algorithms.ranking.RankingAlgorithm;

import java.util.ServiceLoader;

/**
 * Ein Dienstleister zur Bereitstellung von {@link java.util.ServiceLoader}-Objekten der unterschiedlichen Algorithmentypen.
 * Diese umfassen Adaptionsalgorithmen, Paarungsalgorithmen und Bewertungsalgorithmen.
 *
 * @author Simon Hügel
 */
public class AlgorithmLoader {

    private ServiceLoader<AdaptationAlgorithm> adaptAlgLoader = ServiceLoader.load(AdaptationAlgorithm.class);
    private ServiceLoader<PairingAlgorithm> pairAlgLoader = ServiceLoader.load(PairingAlgorithm.class);
    private ServiceLoader<RankingAlgorithm> rankAlgLoader = ServiceLoader.load(RankingAlgorithm.class);

    public ServiceLoader<AdaptationAlgorithm> getAdaptAlgLoader() {
        return adaptAlgLoader;
    }

    public ServiceLoader<PairingAlgorithm> getPairAlgLoader() {
        return pairAlgLoader;
    }

    public ServiceLoader<RankingAlgorithm> getRankAlgLoader() {
        return rankAlgLoader;
    }
}

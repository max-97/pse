package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie die einem Agenten und seinem Gegenspieler eine Aktion zuordnet.
 * Die Basisstrategie kann sowohl vom Agenten abhängen dessen kombinierte Strategie sie beinhaltet, als auch
 * von dessem Gegenspieler.
 * @author Michel Bodé
 */
public abstract class BaseStrategy {
    /**
     * Berechnet die Aktion des Agenten entsprechend der Basisstrategie.
     * @param agent1 Agent dessen kombinierte Strategie die Basisstrategie behinhaltet
     * @param agent2 Gegenspieler
     * @return eine {@code Action} die entweder Kooperation oder Defektion ist
     */
    public abstract Action calculateAction(Agent agent1, Agent agent2);
}

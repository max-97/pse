package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent kooperiert, wenn der Gegenspieler bei allen vorherigen
 * gemeinsamen Spielen kooperiert hat. Handelt es sich um das erste gemeinsame Spiel, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class GrimIndividual implements BaseStrategy {
    public final static String NAME = "";
    public final static String DESCRIPTION = "";

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}

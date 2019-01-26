package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent kooperiert, wenn der Gegenspieler aus dem letzten Spiel
 * kooperiert hat. Handelt es sich um das erste Spiel, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class TitForTatEverybody implements BaseStrategy {
    public final static String NAME = "";
    public final static String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        if(agent1.getHistory().getOpponentCooperated()) {
            return Action.COOPERATION;
        }else {
            return Action.DEFECTION;
        }
    }

    @Override
    public String getName() {
        return null;
    }
}

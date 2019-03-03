package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent kooperiert, wenn der Gegenspieler bei allen vorherigen
 * Spielen kooperiert hat. Handelt es sich um das erste Spiel, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class GrimEverybody implements BaseStrategy {
    public final static String NAME = "Grim mit jedem";
    public final static String DESCRIPTION = "";
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        if(agent2.getHistory().getAlwaysCooperated()) {
            return Action.COOPERATION;
        }else {
            return Action.DEFECTION;
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}

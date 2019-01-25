package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent nie kooperiert.
 * @author Michel Bodé
 */
public class NeverCooperate implements BaseStrategy {
    public final static String NAME = "";
    public final static String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        return Action.DEFECTION;
    }

    @Override
    public String getName() {
        return null;
    }
}

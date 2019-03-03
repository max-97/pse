package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent kooperiert, wenn der Gegenspieler beim letzten gemeinsamen Spiel
 * kooperiert hat. Handelt es sich um das erste gemeinsame Spiel, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class TitForTatIndividual implements BaseStrategy {
    public final static String NAME = "Tit-for-Tat individuell";
    public final static String DESCRIPTION = "";
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        if(agent1.getHistory().cooperatedLastTime(agent2)) {
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

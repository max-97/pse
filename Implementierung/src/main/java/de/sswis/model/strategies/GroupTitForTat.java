package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent, aus Gruppe G, kooperiert, wenn der Gegenspieler beim letzten Spiel
 * gegen einen Agenten aus Gruppe G kooperiert hat. Hat der Gegenspieler noch nicht gegen einen Agenten
 * aus Gruppe G gespielt, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class GroupTitForTat implements BaseStrategy {
    public final static String NAME = "";
    public final static String DESCRIPTION = "";
    public static final int PARAMETER_COUNT = 0;
    public static final String[] PARAMETER_NAMES = {};

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        if(agent2.getHistory().groupCooperatedLastTime(agent1.getGroup())) {
            return Action.COOPERATION;
        }else {
            return Action.DEFECTION;
        }
    }
}

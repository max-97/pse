package de.sswis.model.strategies;

import de.sswis.model.Action;
import de.sswis.model.Agent;

/**
 * Eine Basisstrategie, bei der der Agent, aus Gruppe G, kooperiert, wenn der Gegenspieler bei allen vorherigen
 * Spielen gegen einen Agenten aus Gruppe G kooperiert hat. Hat der Gegenspieler noch nicht gegen einen
 * Agenten aus Gruppe G gespielt, so kooperiert der Agent.
 * @author Michel Bodé
 */
public class GroupGrim implements BaseStrategy {
    public final static String NAME = "";
    public final static String DESCRIPTION = "";

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}

package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung zur Auswahl einer Basisstrategie innerhalb einer kombinierten Strategie.
 * Die Bedingung kann sowohl vom Agenten abhaengen dessen kombinierte Strategie sie beinhaltet, als auch
 * von dessem Gegenspieler.
 * @author Michel Bodé
 */
public interface Condition {
    /**
     * ueberprueft ob die zwei Agenten die Bedingung erfuellen.
     * @param agent1 Agent dessen kombinierte Strategie die Bedingung behinhaltet
     * @param agent2 Gegenspieler
     * @return {@code true}, wenn die Bedingung erfuellt ist, {@code false} sonst
     */
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);

    String getName();

	void setParameter(double conditionParameter);
}

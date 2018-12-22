package de.sswis.model.conditions;

import de.sswis.model.Agent;

/**
 * Eine Bedingung zur Auswahl einer Basisstrategie innerhalb einer kombinierten Strategie.
 * Die Bedingung kann sowohl vom Agenten abhängen dessen kombinierte Strategie sie beinhaltet, als auch
 * von dessem Gegenspieler.
 * @author Michel Bodé
 */
public interface Condition {
    /**
     * Überprüft ob die zwei Agenten die Bedingung erfüllen.
     * @param agent1 Agent dessen kombinierte Strategie die Bedingung behinhaltet
     * @param agent2 Gegenspieler
     * @return {@code true}, wenn die Bedingung erfüllt ist, {@code false} sonst
     */
    public abstract boolean fulfillsCondition(Agent agent1, Agent agent2);
}

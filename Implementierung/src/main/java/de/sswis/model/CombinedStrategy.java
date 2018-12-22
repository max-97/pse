package de.sswis.model;

import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

/**
 * Eine kombinierte Strategie bestehend aus Paaren von Basisstrategien und Bedingungen.
 * @author Michel Bodé
 */
public class CombinedStrategy {

    private String name;
    private BaseStrategy[] strategies;
    private Condition[] conditions;

    /**
     * Erstellt eine kombinierte Strategie.
     * @param name Name der Strategie
     * @param strategies Menge an Basisstrategien
     * @param conditions Menge an Bedingungen
     */
    public CombinedStrategy(String name, BaseStrategy[] strategies, Condition[] conditions) {

    }

    public String getName() {
        return name;
    }

    /**
     * Berechnet die Aktion des Agenten um dessen Strategie es sich handelt im Spiel mit einem zweiten Agenten.
     * @param agent1 Agent
     * @param agent2 Gegenspieler
     * @return die Aktion des Agenten
     */
    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}


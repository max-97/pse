package de.sswis.model;

import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;

/**
 * Eine kombinierte Strategie bestehend aus Paaren von Basisstrategien und Bedingungen.
 * @author Michel Bodé
 */
public class CombinedStrategy implements Strategy{

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action calculateAction(Agent agent1, Agent agent2) {
        return null;
    }
}


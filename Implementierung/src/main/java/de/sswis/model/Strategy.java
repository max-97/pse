package de.sswis.model;

/**
 * Eine Strategie welche sowohl eine einfache als auch eine gemischte Strategie sein kann.
 * @author Michel Bodé
 */
public interface Strategy {

    /**
     * Gibt den Namen der Strategie zurück.
     * @return Name
     */
    String getName();

    /**
     * Berechnet die Aktion des Agenten um dessen Strategie es sich handelt im Spiel mit einem zweiten Agenten.
     * @param agent1 Agent
     * @param agent2 Gegenspieler
     * @return die Aktion des Agenten
     */
    Action calculateAction(Agent agent1, Agent agent2);

}

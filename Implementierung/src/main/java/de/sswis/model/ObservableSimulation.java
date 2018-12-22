package de.sswis.model;

import de.sswis.controller.SimulationObserver;

/**
 * Eine {@code ObservableSimulation} die von mehreren {@code SimulationObserver} beobachtet werden kann.
 * Die {@code SimulationObserver} können so auf Änderungen des Zustands der {@code ObservableSimulation}
 * hingewiesen werden.
 * @author Michel Bodé
 */
public interface ObservableSimulation {

    /**
     * Fügt der {@code ObservableSimulation} einen Beobachter hinzu.
     * @param o hinzuzufügender Beobachter
     */
    void addObserver(SimulationObserver o);

    /**
     * Benachrichtigt alle Beobachter.
     */
    void notifyObservers();

    /**
     * Entfernt einen Beobachter aus der {@code ObservableSimulation}.
     * @param o zu entfernender Beobachter
     */
    void deleteObserver(SimulationObserver o);
}

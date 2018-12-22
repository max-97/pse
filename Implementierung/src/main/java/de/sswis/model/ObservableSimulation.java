package de.sswis.model;

import de.sswis.controller.SimulationObserver;

/**
 * Eine {@code ObservableSimulation} die von mehreren {@code SimulationObserver} beobachtet werden kann.
 * Die {@code SimulationObserver} koennen so auf Aenderungen des Zustands der {@code ObservableSimulation}
 * hingewiesen werden.
 * @author Michel Bodé
 */
public interface ObservableSimulation {

    /**
     * Fuegt der {@code ObservableSimulation} einen Beobachter hinzu.
     * @param o hinzuzufuegender Beobachter
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

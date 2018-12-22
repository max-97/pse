package de.sswis.model;

import de.sswis.controller.SimulationObserver;

public interface ObservableSimulation {

    void addObserver(SimulationObserver o);

    void notifyObservers();

    void deleteObserver(SimulationObserver o);
}

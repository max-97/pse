package de.sswis.controller;

import de.sswis.model.Simulation;

public interface SimulationObserver {
    void update(Simulation sim);
}

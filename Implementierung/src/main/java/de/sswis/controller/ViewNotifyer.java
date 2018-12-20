package de.sswis.controller;

import de.sswis.model.Simulation;
import de.sswis.view.AbstractMainView;

public class ViewNotifyer implements SimulationObserver {

    private AbstractMainView mainView;

    public ViewNotifyer(AbstractMainView mainView) {

    }

    @Override
    public void update(Simulation sim) {

    }
}

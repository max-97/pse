package de.sswis.controller.handlers;

import de.sswis.view.AbstractMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Bricht die ausgewählte {@code Simulation} ab. Es werden keine Ergebnisse der {@code Simulation} gespeichert.
 *
 * @author Max Braun
 */
public class StopSimulationHandler implements ActionListener {

    AbstractMainView mainView;

    /**
     *
     * @param mainView Hauptfenster mit der ausgewählten {@code Simulation}
     */
    public StopSimulationHandler(AbstractMainView mainView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

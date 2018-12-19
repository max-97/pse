package de.sswis.controller.handlers;

import de.sswis.view.AbstractMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Startet die ausgewählten {@code Simulationen}. Die {@code Simulationen} werden im Hauptfenster ausgewählt und sind
 * durch ihre zugehörigen {@code Konfigurationen} identifiziert.
 *
 * @author Max Braun
 */
public class StartSimulationHandler implements ActionListener {

    private AbstractMainView mainView;

    /**
     *
     * @param mainView Hauptfenster mit den ausgewählten {@code Simulationen}
     */
    public StartSimulationHandler(AbstractMainView mainView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

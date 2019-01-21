package de.sswis.controller;

import de.sswis.model.Simulation;
import de.sswis.view.AbstractMainView;

import javax.swing.*;

/**
 * Benachrichtigt die View über beendete {@code Simulationen}. Wird eine Simulation beendet benachrichtigt dieser
 * {@code SimulationObserver} die View. Dazu wird die entsprechende {@code Simulation} im Hauptfenster als
 * abgeschlossen markiert.
 *
 * @author Max Braun
 */
public class ViewNotifier implements SimulationObserver {

    private AbstractMainView mainView;

    /**
     * Konstruktor von {@code ViewNotifier}
     * @param mainView das Hauptfenster, das benachrichtigt wird
     */
    public ViewNotifier(AbstractMainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void update(Simulation sim) {
        SwingUtilities.invokeLater(() -> mainView.setSimulationFinished(sim.getName()));
    }
}

package de.sswis.controller;

import de.sswis.model.Simulation;
import de.sswis.view.AbstractMainView;

/**
 * Benachrichtig die View über beendete {@code Simulationen}. Wird eine Simulation beendet benachrichtigt dieser
 * {@code SimulationObserver} die View. Dazu wird die entsprechende {@code Simulation} im Hauptfenster als
 * abgeschlossen markiert.
 *
 * @author Max Braun
 */
public class ViewNotifyer implements SimulationObserver {

    private AbstractMainView mainView;

    /**
     * Konstruktor von {@code ViewNotifyer}
     * @param mainView das Hauptfenster, das benachrichtig wird
     */
    public ViewNotifyer(AbstractMainView mainView) {

    }

    @Override
    public void update(Simulation sim) {

    }
}

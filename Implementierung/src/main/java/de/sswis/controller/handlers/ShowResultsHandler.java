package de.sswis.controller.handlers;

import de.sswis.controller.AbstractGuiFactory;
import de.sswis.view.AbstractMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet die View mit der Ergebnissansicht der ausgewählten {@code Konfigurationen}. Die {@code Konfigurationen} werden
 * im Hauptfenster ausgewählt.
 *
 * @author Max Braun
 */
public class ShowResultsHandler implements ActionListener {

    private AbstractGuiFactory Factory;
    private AbstractMainView mainView;

    /**
     *
     * @param mainView Hauptfenster mit den ausgewählten {@code Konfigurationen}
     * @param factory Fabrik zum Erstellen der View
     */
    public ShowResultsHandler(AbstractMainView mainView, AbstractGuiFactory factory) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

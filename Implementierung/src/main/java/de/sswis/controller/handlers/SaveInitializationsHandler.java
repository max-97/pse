package de.sswis.controller.handlers;

import de.sswis.view.AbstractNewInitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die erstellte {@code Initialisierung}. Die View, die diesen {@code ActionListener} verwendet muss eine
 * {@code Initialisierung} besitzen.
 *
 * @author Max Braun
 */
public class SaveInitializationsHandler implements ActionListener {

    private AbstractNewInitializationView initializationView;

    /**
     *
     * @param initializationView die View mit der zu speichernden Initialisierung
     */
    public SaveInitializationsHandler(AbstractNewInitializationView initializationView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

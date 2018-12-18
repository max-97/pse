package de.sswis.controller.handlers;

import de.sswis.view.Startfenster;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die erstellten {@code Ergebnisse}. Die View, die diesen {@code ActionListener} verwendet muss
 * {@code Ergebnisse} besitzen.
 *
 * @author Max Braun
 */
public class SaveResultsHandler implements ActionListener {

    private Startfenster mainView;

    /**
     *
     * @param mainView die View mit den zu speichernden {@code Ergebnissen}
     */
    public SaveResultsHandler(Startfenster mainView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

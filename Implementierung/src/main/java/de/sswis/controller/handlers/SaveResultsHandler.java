package de.sswis.controller.handlers;

import de.sswis.view.AbstractMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die erstellten {@code Ergebnisse}. Die View, die diesen {@code ActionListener} verwendet muss
 * {@code Ergebnisse} besitzen.
 *
 * @author Max Braun
 */
public class SaveResultsHandler implements ActionListener {

    private AbstractMainView mainView;

    /**
     *
     * @param mainView die View mit den zu speichernden {@code Ergebnissen}
     */
    public SaveResultsHandler(AbstractMainView mainView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

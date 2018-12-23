package de.sswis.controller.handlers;

import de.sswis.view.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Speichert die ViewModel-Objekte in der View und schließt die View.
 *
 * @author Max Braun
 */
public class SaveAndQuitHandler implements ActionListener {

    private AbstractView view;

    /**
     *
     * @param view die zu schließende View
     */
    public SaveAndQuitHandler(AbstractView view) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package de.sswis.controller.handlers;

import de.sswis.view.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ein {@code ActionListener} zum Schließen einer View.
 *
 * @author Max Braun
 */
public class CancleHandler implements ActionListener {

    private AbstractView view;

    /**
     *
     * @param view die zu schließende View
     */
    public CancleHandler(AbstractView view) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

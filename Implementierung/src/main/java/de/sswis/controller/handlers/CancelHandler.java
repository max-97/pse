package de.sswis.controller.handlers;

import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ein {@code ActionListener} zum Schließen einer View.
 *
 * @author Max Braun
 */
public class CancelHandler implements ActionListener {

    private AbstractView view;

    /**
     *
     * @param view die zu schließende View
     */
    public CancelHandler(AbstractView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.view.close();
    }
}

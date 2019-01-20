package de.sswis.controller.handlers;

import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageResultsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht das ausgewählte {@code Ergebnis}.
 *
 * @author Max Braun
 */
public class DeleteResultHandler implements ActionListener {

    private AbstractMainView mainView;

    /**
     *
     * @param mainView View, welche das zu löschende {@code Result} beinhaltet
     */
    public DeleteResultHandler(AbstractManageResultsView mainView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

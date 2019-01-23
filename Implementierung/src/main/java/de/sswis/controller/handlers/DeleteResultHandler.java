package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractManageResultsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht das ausgewählte {@code Ergebnis}.
 *
 * @author Max Braun
 */
public class DeleteResultHandler implements ActionListener {

    private AbstractManageResultsView manageResultsView;
    private FileManager fileManager;

    /**
     *
     * @param manageResultsView View, welche das zu löschende {@code Result} beinhaltet
     */
    public DeleteResultHandler(AbstractManageResultsView manageResultsView) {
        this.manageResultsView = manageResultsView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

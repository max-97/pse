package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Löscht das ausgewählte {@code Ergebnis}.
 *
 * @author Max Braun
 */
public class DeleteResultHandler implements ActionListener {

    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param mainView View, welche das zu löschende {@code Result} beinhaltet
     */
    public DeleteResultHandler(AbstractMainView mainView) {
        this.mainView = mainView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

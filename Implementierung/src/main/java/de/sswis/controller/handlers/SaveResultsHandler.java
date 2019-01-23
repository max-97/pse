package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Speichert die erstellten {@code Ergebnisse}. Die View, die diesen {@code ActionListener} verwendet muss
 * {@code Ergebnisse} besitzen.
 *
 * @author Max Braun
 */
public class SaveResultsHandler implements ActionListener {

    private AbstractMainView mainView;
    private FileManager fileManager;

    /**
     *
     * @param mainView die View mit den zu speichernden {@code Ergebnissen}
     */
    public SaveResultsHandler(AbstractMainView mainView) {
        this.mainView = mainView;
        this.fileManager = new FileManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (VMResult r : this.mainView.getResults()) {
            this.fileManager.saveResult(r);
        }
    }
}

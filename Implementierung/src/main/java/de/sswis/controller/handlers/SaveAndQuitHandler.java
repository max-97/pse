package de.sswis.controller.handlers;

import de.sswis.controller.FileManager;
import de.sswis.controller.ModelParser;
import de.sswis.view.AbstractMainView;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.AbstractView;
import de.sswis.view.model.VMConfiguration;

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
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.view instanceof AbstractManageConfigurationsView) {
            AbstractManageConfigurationsView mcView = (AbstractManageConfigurationsView) this.view;
            AbstractMainView mainView = mcView.getParentView();
            FileManager manager = new FileManager();
            for (VMConfiguration c : manager.loadAllConfigurations()) {
                mainView.addConfiguration(c);
            }
            mainView.update();
        }
        this.view.close();
    }
}

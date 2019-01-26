package de.sswis.controller.handlers;

import de.sswis.controller.ModelProvider;
import de.sswis.model.Configuration;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Bricht die ausgewählte {@code Simulation} ab. Es werden keine Ergebnisse der {@code Simulation} gespeichert.
 *
 * @author Max Braun
 */
public class StopSimulationHandler implements ActionListener {

    private AbstractMainView mainView;
    private ModelProvider provider;

    /**
     *
     * @param mainView Hauptfenster mit der ausgewählten {@code Simulation}
     */
    public StopSimulationHandler(AbstractMainView mainView) {
        this.mainView = mainView;
        this.provider = ModelProvider.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        VMConfiguration configurations = mainView.getSelected();

        Configuration config = this.provider.getConfiguration(configurations.getName());
        config.getSimulation().abort();
    }
}

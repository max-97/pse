package de.sswis.controller.handlers;

import de.sswis.controller.ModelProvider;
import de.sswis.model.Configuration;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        ArrayList<VMConfiguration> configurations = new ArrayList<>();
        configurations.add(mainView.getSelected());
        for (VMConfiguration c : configurations) {
            Configuration config = this.provider.getConfiguration(c.getName());
            config.getSimulation().abort();
        }
    }
}

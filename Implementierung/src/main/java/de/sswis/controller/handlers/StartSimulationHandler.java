package de.sswis.controller.handlers;

import de.sswis.controller.ModelProvider;
import de.sswis.controller.ViewNotifier;
import de.sswis.model.Configuration;
import de.sswis.model.Simulation;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMConfiguration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Startet die ausgewählten {@code Simulationen}. Die {@code Simulationen} werden im Hauptfenster ausgewählt und sind
 * durch ihre zugehörigen {@code Konfigurationen} identifiziert.
 *
 * @author Max Braun
 */
public class StartSimulationHandler implements ActionListener {

    private AbstractMainView mainView;
    private ModelProvider provider;

    /**
     *
     * @param mainView Hauptfenster mit den ausgewählten {@code Simulationen}
     */
    public StartSimulationHandler(AbstractMainView mainView) {
        this.mainView = mainView;
        this.provider = ModelProvider.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO make mainView.getSelected return a collection
        ArrayList<VMConfiguration> selected = new ArrayList<>();
        selected.add(mainView.getSelected());

        for (VMConfiguration c : selected) {
            Configuration config = this.provider.getConfiguration(c.getName());
            Simulation sim = config.simulate();
            sim.addObserver(new ViewNotifier(this.mainView));
            new Thread(sim).start();
        }
    }
}

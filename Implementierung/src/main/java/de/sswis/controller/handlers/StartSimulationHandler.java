package de.sswis.controller.handlers;

import de.sswis.controller.ModelProvider;
import de.sswis.controller.MultiViewNotifier;
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
        int repetitions = mainView.askForRepetitionNumber();
        if (repetitions <= 0)
            return;

        VMConfiguration selected = mainView.getSelected();
        if (!selected.isMultiConfiguration()) {
            Configuration config = this.provider.getConfiguration(selected.getName());
            Simulation sim = config.simulate();
            sim.setRepetitions(repetitions);
            sim.addObserver(new ViewNotifier(this.mainView));
            new Thread(sim).start();
        } else {
            Collection<Configuration> configurations = new ArrayList<>();
            String name = selected.getName();
            for (int i = 1; this.provider.getConfiguration(name + "_" + i) != null; i++) {
                configurations.add(this.provider.getConfiguration(name + "_" + i));
            }

            MultiViewNotifier mvn = new MultiViewNotifier(mainView, configurations.size());
            for (Configuration c : configurations) {
                Simulation sim = c.simulate();
                sim.setRepetitions(repetitions);
                sim.addObserver(mvn);
                new Thread(sim).start();
            }
        }
        mainView.setSimulationStarted(selected.getName());
    }
}

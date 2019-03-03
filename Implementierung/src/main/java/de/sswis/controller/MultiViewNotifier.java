package de.sswis.controller;

import de.sswis.model.Simulation;
import de.sswis.view.AbstractMainView;
import de.sswis.view.model.VMResult;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class MultiViewNotifier implements SimulationObserver {

    private AbstractMainView mainView;
    private int instances;
    private int counter;
    private ArrayList<Simulation> finishedSim;

    public MultiViewNotifier(AbstractMainView view, int instances) {
        this.mainView = view;
        this.instances = instances;
        this.counter = 0;
        this.finishedSim = new ArrayList<>();
    }

    @Override
    public synchronized void update(Simulation sim) {
        if (counter != instances - 1) {
            counter++;
            this.finishedSim.add(sim);
        } else {
            this.finishedSim.add(sim);
            Simulation first = this.finishedSim.get(0);
            Collection<VMResult> results = new ArrayList<>();
            ModelParser parser = new ModelParser();
            for (Simulation s : finishedSim) {
                results.addAll(parser.parseSimulationToVMResult(s));
            }
            String name = first.getName().replaceFirst("_\\d+", "");
            SwingUtilities.invokeLater(() -> {
                this.mainView.setSimulationFinished(name);
                this.mainView.setResults(name, results);
            });
        }
    }
}

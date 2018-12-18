package de.sswis.controller.handlers;

import de.sswis.view.StrategienMenue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Speichert die erstellte {@code kombinierte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code kombinierte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveStrategiesHandler implements ActionListener {

    private StrategienMenue strategyView;

    /**
     *
     * @param strategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveStrategiesHandler(StrategienMenue strategyView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

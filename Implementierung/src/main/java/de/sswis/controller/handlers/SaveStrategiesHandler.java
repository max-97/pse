package de.sswis.controller.handlers;

import de.sswis.view.AbstractNewStrategyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Speichert die erstellte {@code gemischte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code gemischte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveStrategiesHandler implements ActionListener {

    private AbstractNewStrategyView strategyView;

    /**
     *
     * @param strategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveStrategiesHandler(AbstractNewStrategyView strategyView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

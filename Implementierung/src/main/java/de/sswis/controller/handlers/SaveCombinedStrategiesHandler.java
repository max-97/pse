package de.sswis.controller.handlers;

import de.sswis.view.AbstractNewCombinedStrategyView;
import de.sswis.view.AbstractNewStrategyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Speichert die erstellte {@code kombinierte Strategie}. Die View, die diesen {@code ActionListener} verwendet muss
 * eine {@code kombinierte Strategie} besitzen.
 *
 * @author Max Braun
 */
public class SaveCombinedStrategiesHandler implements ActionListener {

    private AbstractNewStrategyView strategyView;

    /**
     *
     * @param strategyView die View mit der zu speichernden {@code kombinierten Strategie}
     */
    public SaveCombinedStrategiesHandler(AbstractNewCombinedStrategyView strategyView) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

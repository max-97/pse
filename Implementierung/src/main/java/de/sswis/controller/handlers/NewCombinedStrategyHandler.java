package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neue kombinierte Strategie. In der View zum Verwalten der {@code kombinierten Strategien} wird eine
 * neue {@code kombinierte Strategie} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen
 * {@code kombinierte Strategie}.
 *
 * @author Max Braun
 */
public class NewCombinedStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewCombinedStrategyHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

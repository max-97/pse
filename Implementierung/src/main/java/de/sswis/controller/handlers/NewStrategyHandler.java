package de.sswis.controller.handlers;

import de.sswis.controller.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Erstellt eine neue kombinierte Strategie. In der View zum Verwalten der {@code gemischten Strategien} wird eine
 * neue {@code gemischte Strategie} hinzugefügt und es öffnet sich die View zum Bearbeiten der neuen
 * {@code gemischten Strategie}.
 *
 * @author Max Braun
 */
public class NewStrategyHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewStrategyHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

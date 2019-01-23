package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen einer {@code kombinierte Strategie}.
 *
 * @author Max Braun
 */
public class NewCombinedStrategyViewHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewCombinedStrategyViewHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Öffnet eine View zum Erstellen einer {@code gemischten Strategie}.
 *
 * @author Max Braun
 */
public class NewStrategyViewHandler implements ActionListener {

    private AbstractGuiFactory factory;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewStrategyViewHandler(AbstractGuiFactory factory) {
        this.factory = factory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewStrategyView newStrategyView = this.factory.createNewStrategyView();
        newStrategyView.setParentView(null);
        newStrategyView.setStrategy(new VMStrategy());
        newStrategyView.update();
        newStrategyView.show();
    }
}

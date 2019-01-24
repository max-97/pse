package de.sswis.controller.handlers;

import de.sswis.view.AbstractGuiFactory;
import de.sswis.view.AbstractManageStrategiesView;
import de.sswis.view.AbstractNewStrategyView;
import de.sswis.view.model.VMStrategy;

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
    private AbstractManageStrategiesView strategiesView;

    /**
     *
     * @param factory Fabrik zum Erstellen der View
     */
    public NewStrategyHandler(AbstractGuiFactory factory, AbstractManageStrategiesView view) {
        this.factory = factory;
        this.strategiesView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractNewStrategyView newStrategyView = this.factory.createNewStrategyView();
        newStrategyView.setParentView(strategiesView);
        newStrategyView.setStrategy(new VMStrategy());
        newStrategyView.update();
        newStrategyView.show();
    }
}

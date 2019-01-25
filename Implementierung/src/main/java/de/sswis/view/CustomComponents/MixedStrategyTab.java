package de.sswis.view.CustomComponents;

import de.sswis.view.model.VMStrategy;

import javax.swing.*;

/**
 * Ein Tab fuer eine gemischte Strategie, der in der ManageStrategiesView angezeigt wird.
 */
public class MixedStrategyTab {

    private VMStrategy vmStrategy;

    private JPanel MainPanel;
    private JTextPane descriptionTextPane;
    private JButton deleteButton;
    private JButton editButton;
    private JLabel nameLabel;

    public MixedStrategyTab(VMStrategy vmStrategy) {
        this.vmStrategy = vmStrategy;
    }

    private void addProbabiltiesLines() {}
}

package de.sswis.view.CustomComponents;

import de.sswis.view.model.VMConfiguration;

import javax.swing.*;

/**
 * Ein Tab fuer eine Konfiguration, der in der ManageConfigurationsView angezeigt wird.
 */
public class ConfigurationTab {

    private VMConfiguration vmConfiguration;

    private JPanel MainPanel;
    private JLabel nameGameLabel;
    private JLabel nameInitializationLabel;
    private JLabel namePairingAlgoLabel;
    private JLabel nameRankingAlgoLabel;
    private JLabel nameAdaptionAlgoLabel;
    private JLabel probalityAdaptionLabel;
    private JLabel numberOfRoundsLabel;
    private JLabel maxNumberOfCyclesLabel;
    private JLabel nameConfigurationLabel;

    public ConfigurationTab(VMConfiguration vmConfiguration) {
        this.vmConfiguration = vmConfiguration;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

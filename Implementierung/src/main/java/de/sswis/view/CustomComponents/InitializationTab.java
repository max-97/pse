package de.sswis.view.CustomComponents;

import de.sswis.view.model.VMInitialization;

import javax.swing.*;

/**
 * Ein Tab fuer eine Initialisierung, der in der ManageInitializationsView angezeigt wird.
 */
public class InitializationTab {

    private VMInitialization vmInitialization;

    private JPanel MainPanel;
    private JTextArea descriptionTextArea;
    private JLabel nameInitializationLabel;

    public InitializationTab(VMInitialization vmInitialization) {
        this.vmInitialization = vmInitialization;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

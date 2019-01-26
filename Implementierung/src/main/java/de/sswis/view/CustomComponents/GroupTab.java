package de.sswis.view.CustomComponents;

import de.sswis.view.model.VMGroup;

import javax.swing.*;
import java.util.List;

/**
 * Ein Tab fuer eine Gruppe, der in der NewInitializationView angezeigt wird.
 */
public class GroupTab {
    private VMGroup vmGroup;
    private List<String> allStrategies;

    private JPanel MainPanel;
    private JTabbedPane tabbedPane2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JFormattedTextField formattedTextField6;
    private JFormattedTextField formattedTextField7;
    private JTabbedPane initialStrategiesTabbedPane;
    private JTextField textField3;
    private JButton neuesStartkapitalHinzufuegenButton;
    private JTabbedPane capitalsTabbedPane;
    private JLabel groupIDLabel;

    public GroupTab(VMGroup vmGroup, List<String> allStrategies) {
        this.vmGroup = vmGroup;
        this.allStrategies = allStrategies;
    }

    private void addStrategyTabs() {}

    private void addCapital() {}

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

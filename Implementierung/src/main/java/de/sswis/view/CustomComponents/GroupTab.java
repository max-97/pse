package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMGroup;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Tab fuer eine Gruppe, der in der NewInitializationView angezeigt wird.
 */
public class GroupTab {
    private VMGroup vmGroup;
    private List<String> allStrategies;
    private List<InitialStrategyTab> strategyTabs;
    private List<StartCapitalTab> startCapitalTabs;

    private JPanel MainPanel;

    private JTabbedPane initGroupTabbedPane;

    private ButtonGroup buttonGroup;
    private JRadioButton idAgentRadioButton;
    private JRadioButton percentageAgentRadioButton;
    private JFormattedTextField idAgentTextField;
    private JFormattedTextField percentageAgentTextField;
    private JTabbedPane initialStrategiesTabbedPane;
    private JTextField groupNameTextField;
    private JButton neuesStartkapitalHinzufuegenButton;
    private JTabbedPane capitalsTabbedPane;
    private JLabel groupIDLabel;
    private JButton addCapitalButton;
    private JLabel idLabel;
    private JLabel percentageLabel;

    public GroupTab(List<String> allStrategies) {
        this.vmGroup = new VMGroup();
        this.allStrategies = allStrategies;

        strategyTabs = new ArrayList<InitialStrategyTab>();
        $$$setupUI$$$();
        createStrategyTabs();

        startCapitalTabs = new ArrayList<StartCapitalTab>();
    }



    private void createStrategyTabs() {
        for (int i = 0; i < allStrategies.size(); i++) {
            strategyTabs.add(new InitialStrategyTab(allStrategies.get(i)));
        }
    }

    private void addStrategyTabs() {
        for (int i = 0; i < strategyTabs.size(); i++) {
            initialStrategiesTabbedPane.addTab(strategyTabs.get(i).getTitle(),
                    strategyTabs.get(i).$$$getRootComponent$$$());

        }
    }

    private void addCapital() {
        StartCapitalTab capitalTab = new StartCapitalTab();
        startCapitalTabs.add(capitalTab);
        capitalsTabbedPane.addTab(capitalTab.getTitle(), capitalTab.$$$getRootComponent$$$());
    }


    public void update() {
        percentageAgentTextField.setEnabled(percentageAgentRadioButton.isSelected());
        percentageLabel.setEnabled(percentageAgentRadioButton.isSelected());

        idAgentTextField.setEnabled(idAgentRadioButton.isSelected());
        idLabel.setEnabled(idAgentRadioButton.isSelected());

        for (int i = 0; i < strategyTabs.size(); i++) {
            strategyTabs.get(i).update();
        }

        for (int i = 0; i < startCapitalTabs.size(); i++) {
            startCapitalTabs.get(i).update();
        }

        updateVM();

    }

    public void updateVM() {

        vmGroup.setName(groupNameTextField.getText());

        //TODO: VMGruppe setter?!
    }

    public String getTitle() {
        String title = vmGroup.getId() + ": ";

        if (vmGroup.getName() == null) {
            title = title + "unbenannt";
        } else {
            title = title + vmGroup.getName();
        }

        return title;
    }

    public void setVMGroup(VMGroup vmGroup) {
        this.vmGroup = vmGroup;

        groupIDLabel.setText(vmGroup.getId() + "");
        groupNameTextField.setText(vmGroup.getName());
        /*
        if () {
            idAgentRadioButton.setSelected(true);
            idAgentTextField.setText(vmGroup.getAgents().getAgentIDs() + "");

        } else {
            percentageAgentRadioButton.setSelected(true);
            percentageAgentTextField.setText(vmGroup.getAgents().getPercentage() + "");
        }
         */

        //TODO: implement strategies and capitals

    }

    public VMGroup getVmGroup() {
        return vmGroup;
    }

    private void createUIComponents() {

        groupIDLabel = new JLabel(vmGroup.getId() + "");

        idAgentRadioButton = new JRadioButton();
        percentageAgentRadioButton = new JRadioButton();
        buttonGroup = new ButtonGroup();
        buttonGroup.add(idAgentRadioButton);
        buttonGroup.add(percentageAgentRadioButton);

        addStrategyTabs();

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(10, 10, 10, 10), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        initGroupTabbedPane = new JTabbedPane();
        panel1.add(initGroupTabbedPane, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(6, 4, new Insets(10, 10, 10, 10), -1, -1));
        initGroupTabbedPane.addTab("Agenten", panel2);
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        idAgentRadioButton.setSelected(true);
        idAgentRadioButton.setText("Wähle Agenten nach ihren IDs");
        panel2.add(idAgentRadioButton, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        percentageAgentRadioButton.setText("Wähle Agenten nach prozentualem Anteil");
        panel2.add(percentageAgentRadioButton, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idLabel = new JLabel();
        idLabel.setText("IDs:");
        panel2.add(idLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAgentTextField = new JFormattedTextField();
        panel2.add(idAgentTextField, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        percentageLabel = new JLabel();
        percentageLabel.setEnabled(false);
        percentageLabel.setText("Anteil:");
        panel2.add(percentageLabel, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        percentageAgentTextField = new JFormattedTextField();
        percentageAgentTextField.setEnabled(false);
        panel2.add(percentageAgentTextField, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Bestimme die Agenten, die dieser Gruppe angehören sollen...");
        panel2.add(label1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        initGroupTabbedPane.addTab("Initiale Strategien", panel3);
        initialStrategiesTabbedPane = new JTabbedPane();
        initialStrategiesTabbedPane.setTabLayoutPolicy(1);
        initialStrategiesTabbedPane.setTabPlacement(2);
        panel3.add(initialStrategiesTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        initGroupTabbedPane.addTab("Startkapital", panel4);
        capitalsTabbedPane = new JTabbedPane();
        capitalsTabbedPane.setTabLayoutPolicy(1);
        capitalsTabbedPane.setTabPlacement(2);
        panel4.add(capitalsTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        addCapitalButton = new JButton();
        addCapitalButton.setText("Neues Startkapital hinzufügen");
        panel4.add(addCapitalButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Gruppen ID: ");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Gruppenname: ");
        panel1.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        groupNameTextField = new JTextField();
        groupNameTextField.setText("");
        panel1.add(groupNameTextField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        groupIDLabel.setText("Label");
        panel1.add(groupIDLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.NewInitializationView;
import de.sswis.view.model.VMGroup;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Ein Tab fuer eine Gruppe, der in der NewInitializationView angezeigt wird.
 */
public class GroupTab {

    private NewInitializationView initializationView;


    private VMGroup vmGroup;
    private List<String> allStrategies;
    private List<InitialStrategyTab> strategyTabs;
    private List<StartCapitalTab> startCapitalTabs;


    private JPanel MainPanel;

    private JTabbedPane initGroupTabbedPane;

    private ButtonGroup buttonGroupCapital;
    private ButtonGroup buttonGroupStrategy;
    private JFormattedTextField idAgentTextField;
    private JFormattedTextField percentageAgentTextField;
    private JTabbedPane initialStrategiesTabbedPane;
    private JTextField groupNameTextField;
    private JTabbedPane capitalsTabbedPane;
    private JLabel groupIDLabel;
    private JButton addCapitalButton;
    private JLabel idLabel;
    private JRadioButton idAgentStrategyRadioButton;
    private JRadioButton percentageAgentStrategyRadioButton;
    private JRadioButton idAgentCapitalRadioButton;
    private JRadioButton percentageAgentCapitalRadioButton;
    private JFormattedTextField distributionTextField;
    private JLabel percentageLabel;

    public GroupTab(List<String> allStrategies) {
        this.vmGroup = new VMGroup();
        this.allStrategies = allStrategies;

        strategyTabs = new ArrayList<InitialStrategyTab>();
        $$$setupUI$$$();
        addStrategyTabs();

        startCapitalTabs = new ArrayList<StartCapitalTab>();
    }


    private void addStrategyTabs() {
        for (int i = 0; i < allStrategies.size(); i++) {
            InitialStrategyTab tab = new InitialStrategyTab(allStrategies.get(i));

            initialStrategiesTabbedPane.addTab(tab.getTitle(), tab.$$$getRootComponent$$$());

            strategyTabs.add(tab);
        }
    }

    private void addCapital() {
        StartCapitalTab capitalTab = new StartCapitalTab();
        capitalTab.addTitleChangeListeners(this);

        startCapitalTabs.add(capitalTab);
        capitalsTabbedPane.addTab(capitalTab.getTitle(), capitalTab.$$$getRootComponent$$$());
    }

    private void addSpecificCapital(String capital, String distribution) {
        StartCapitalTab capitalTab = new StartCapitalTab();
        capitalTab.addTitleChangeListeners(this);
        capitalTab.setStartCapital(capital, distribution);

        startCapitalTabs.add(capitalTab);
        capitalsTabbedPane.addTab(capitalTab.getTitle(), capitalTab.$$$getRootComponent$$$());
    }

    public void updateVM() {

        vmGroup.setName(groupNameTextField.getText());
        //TODO: make setter
        //vmGroup.setRelativeAgentDistributions(!initializationView.isIDAgentDistributionSelected());
        vmGroup.setAgents(distributionTextField.getText());
        vmGroup.setRelativeCapitalDistributions(percentageAgentCapitalRadioButton.isSelected());

        for (int i = 0; i < strategyTabs.size(); i++) {
            vmGroup.addStrategy(allStrategies.get(i), strategyTabs.get(i).getUserInput());

        }

        vmGroup.setRelativeStrategyDistribution(percentageAgentStrategyRadioButton.isSelected());
        for (int i = 0; i < startCapitalTabs.size(); i++) {
            vmGroup.addStartCapital(startCapitalTabs.get(i).getStartCapital(),
                    startCapitalTabs.get(i).getAgentUserInput());
        }
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
        distributionTextField.setText(vmGroup.getAgents());

        percentageAgentStrategyRadioButton.setSelected(vmGroup.getRelativeStrategyDistributions());
        percentageAgentCapitalRadioButton.setSelected(vmGroup.getRelativeCapitalDistributions());

        for (int i = 0; i < vmGroup.getStrategies().size(); i++) {
            List<String> distribution = vmGroup.getStrategyDistributions().get(i);
            //TODO: vm getter anpassen
            //strategyTabs.get(i).setDistribution();
        }

        for (int i = 0; i < vmGroup.getStartCapital().size(); i++) {
            //TODO: vm getter anpassen
            addSpecificCapital(vmGroup.getStartCapital().get(i), );
        }

    }

    public VMGroup getVmGroup() {
        updateVM();
        return vmGroup;
    }

    public void setInitializationView(NewInitializationView initializationView) {
        this.initializationView = initializationView;
    }

    public void updateCapitalsTitle() {
        for (int i = 0; i < startCapitalTabs.size(); i++) {
            capitalsTabbedPane.setTitleAt(i, startCapitalTabs.get(i).getTitle());
        }
    }


    private void createUIComponents() {

        groupIDLabel = new JLabel(vmGroup.getId() + "");

        buttonGroupCapital = new ButtonGroup();

        idAgentCapitalRadioButton = new JRadioButton();
        buttonGroupCapital.add(idAgentCapitalRadioButton);

        percentageAgentCapitalRadioButton = new JRadioButton();
        buttonGroupCapital.add(percentageAgentCapitalRadioButton);


        buttonGroupStrategy = new ButtonGroup();

        idAgentStrategyRadioButton = new JRadioButton();
        buttonGroupStrategy.add(idAgentStrategyRadioButton);

        percentageAgentStrategyRadioButton = new JRadioButton();
        buttonGroupStrategy.add(percentageAgentStrategyRadioButton);

        addCapitalButton.addActionListener(e -> addCapital());

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
        panel2.setLayout(new GridLayoutManager(4, 4, new Insets(10, 10, 10, 10), -1, -1));
        initGroupTabbedPane.addTab("Agenten", panel2);
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        idLabel = new JLabel();
        idLabel.setText("IDs/ Anteil:");
        panel2.add(idLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        distributionTextField = new JFormattedTextField();
        panel2.add(distributionTextField, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Bestimme die Agenten, die dieser Gruppe angehören sollen...");
        panel2.add(label1, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        initGroupTabbedPane.addTab("Initiale Strategien", panel3);
        initialStrategiesTabbedPane = new JTabbedPane();
        initialStrategiesTabbedPane.setTabLayoutPolicy(1);
        initialStrategiesTabbedPane.setTabPlacement(2);
        panel3.add(initialStrategiesTabbedPane, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        idAgentStrategyRadioButton.setSelected(true);
        idAgentStrategyRadioButton.setText("Wähle Agenten nach ihren IDs");
        panel3.add(idAgentStrategyRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        percentageAgentStrategyRadioButton.setText("Wähle Agenten nach prozentualem Anteil");
        panel3.add(percentageAgentStrategyRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        initGroupTabbedPane.addTab("Startkapital", panel4);
        capitalsTabbedPane = new JTabbedPane();
        capitalsTabbedPane.setTabLayoutPolicy(1);
        capitalsTabbedPane.setTabPlacement(2);
        panel4.add(capitalsTabbedPane, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        addCapitalButton.setText("Neues Startkapital hinzufügen");
        panel4.add(addCapitalButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAgentCapitalRadioButton.setSelected(true);
        idAgentCapitalRadioButton.setText("Wähle Agenten nach ihren IDs");
        panel4.add(idAgentCapitalRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        percentageAgentCapitalRadioButton.setText("Wähle Agenten nach prozentualem Anteil");
        panel4.add(percentageAgentCapitalRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

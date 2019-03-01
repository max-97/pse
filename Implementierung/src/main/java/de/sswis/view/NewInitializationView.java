package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.model.Action;
import de.sswis.view.CustomComponents.GroupTab;
import de.sswis.view.model.VMGroup;
import de.sswis.view.model.VMInitialization;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static de.sswis.util.InputValidator.*;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer Initialisierung.
 *
 * @author Sophie Bräuniger
 */
public class NewInitializationView implements AbstractNewInitializationView {

    private JFrame frame = new JFrame();

    private VMInitialization vmInitialization = new VMInitialization();

    private List<GroupTab> groupTabs;

    private List<String> strategies;

    private JPanel panel1;
    private JTabbedPane groupTabbedPane;
    private JTextField agentNumberTextField;
    private JButton finishButton;
    private JButton cancelButton;

    private JTextPane descriptionTextPane;
    private JButton addGroupButton;
    private JFormattedTextField nameTextField;

    private ButtonGroup buttonGroup;
    private JRadioButton idAgentGroupRadioButton;
    private JRadioButton percentageAgentGroupRadioButton;
    private JCheckBox useCapitalCheckBox;

    private AbstractManageInitializationsView parentView;

    public NewInitializationView() {
        groupTabs = new ArrayList<GroupTab>();
        strategies = new ArrayList<String>();

        vmInitialization = new VMInitialization();
    }

    private void addNewGroupTab() {
        GroupTab tab = new GroupTab(strategies);
        //tab.addStrategies(strategies);
        tab.setActionListener(this);

        tab.setID(groupTabbedPane.getTabCount());
        groupTabs.add(tab);
        groupTabbedPane.addTab(tab.getTitle(), tab.$$$getRootComponent$$$());
    }

    public void removeGroupTab(GroupTab tab) {
        UIManager.put("OptionPane.yesButtonText", "Ja");
        UIManager.put("OptionPane.noButtonText", "Nein");
        int n = JOptionPane.showConfirmDialog(null, "Wollen Sie die Gruppe löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            groupTabbedPane.removeTabAt(groupTabs.indexOf(tab));
            groupTabs.remove(tab);

            for (int i = 0; i < groupTabs.size(); i++) {
                groupTabs.get(i).setID(i);
                groupTabbedPane.setTitleAt(i, groupTabs.get(i).getTitle());
            }
        } else if (n == JOptionPane.NO_OPTION) {
            close();
        }
    }

    public void updateGroupTabTitle() {
        for (int i = 0; i < groupTabs.size(); i++) {
            groupTabbedPane.setTitleAt(i, groupTabs.get(i).getTitle());

        }
    }

    private void addSpecificGroupTab(VMGroup vmGroup) {
        GroupTab tab = new GroupTab(strategies);
        //tab.addStrategies(strategies);

        tab.setActionListener(this);

        tab.setVMGroup(vmGroup, percentageAgentGroupRadioButton.isSelected());

        tab.setID(groupTabbedPane.getTabCount());
        groupTabs.add(tab);
        groupTabbedPane.addTab(tab.getTitle(), tab.$$$getRootComponent$$$());
    }

    @Override
    public void update() {
        frame.pack();
    }

    private boolean updateVM() {
        vmInitialization = new VMInitialization();
        String name = nameTextField.getText();
        String desc = descriptionTextPane.getText();
        boolean relDistr = percentageAgentGroupRadioButton.isSelected();
        double percentageSum = 0.0;

        boolean illegalInput = false;

        if (isLegalAgentCount(agentNumberTextField.getText()) && (groupTabs.size() > 0) && isLegalName(name) && isLegalDescription(desc)) {
            vmInitialization.setName(name);
            vmInitialization.setAgentCount(Integer.parseInt(agentNumberTextField.getText())); //TODO: variable?
            vmInitialization.setAddCapitalToTotalPoints(!useCapitalCheckBox.isSelected());
            vmInitialization.setDescription(desc);
            vmInitialization.setRelativeDistribution(relDistr);

            for (int i = 0; i < groupTabs.size(); i++) {
                VMGroup currentGroup = groupTabs.get(i).getVmGroup();

                if (currentGroup != null &&
                        ((relDistr && isDouble(currentGroup.getAgentsString()))
                                || (!relDistr && isIntervalPlusSingleValues(currentGroup.getAgentsString())))) {
                    vmInitialization.addGroup(currentGroup);
                    if (relDistr) percentageSum += Double.parseDouble(currentGroup.getAgentsString());
                } else {
                    illegalInput = true;
                    break;
                }
            }
            if (relDistr && percentageSum != 1.0) illegalInput = true;
        } else illegalInput = true;

        if (illegalInput) {
            JOptionPane.showMessageDialog(frame, ILLEGAL_INPUT_MSG);
            return false;
        }
        return true;

    }

    public boolean isIDAgentDistributionSelected() {
        return idAgentGroupRadioButton.isSelected();
    }

    @Override
    public void show() {
        frame = new JFrame("Initialisierung Bearbeiten");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {
        cancelButton.addActionListener(listener);

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {
        finishButton.addActionListener(listener);
    }


    @Override
    public VMInitialization getVMInitialization() {

        return updateVM() ? this.vmInitialization : null;
    }

    @Override
    public void addStrategy(String name) {
        strategies.add(name);
    }

    @Override
    public void setInitialization(VMInitialization initialization) {

        this.vmInitialization = initialization;
        nameTextField.setText(vmInitialization.getName());
        agentNumberTextField.setText(vmInitialization.getAgentCount() + "");
        useCapitalCheckBox.setSelected(!vmInitialization.addCapitalToTotalPoints());
        descriptionTextPane.setText(initialization.getDescription());
        percentageAgentGroupRadioButton.setSelected(vmInitialization.hasRelativeDistribution());

        for (int i = 0; i < vmInitialization.getGroups().size(); i++) {
            addSpecificGroupTab(vmInitialization.getGroups().get(i));
        }

    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageInitializationsView) parentView;
    }

    @Override
    public AbstractManageInitializationsView getParentView() {
        return this.parentView;
    }

    private void createUIComponents() {
        addGroupButton = new JButton();
        addGroupButton.addActionListener(e -> addNewGroupTab());

        buttonGroup = new ButtonGroup();
        idAgentGroupRadioButton = new JRadioButton();
        buttonGroup.add(idAgentGroupRadioButton);

        percentageAgentGroupRadioButton = new JRadioButton();
        buttonGroup.add(percentageAgentGroupRadioButton);


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(30);
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(16, 3, new Insets(10, 10, 10, 10), -1, -1));
        scrollPane1.setViewportView(panel2);
        final JLabel label1 = new JLabel();
        label1.setText("Name :   ");
        panel2.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Anzahl der Agenten:");
        panel2.add(label2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel2.add(separator1, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        agentNumberTextField = new JTextField();
        agentNumberTextField.setToolTipText("<html> Erlaubt sind positive, ganze, gerade Zahlenwerte (Beispiele: '12', '16', '100') <br> und variable Eingaben der Form Startwert - Endwert - Schrittweite (Beispiele: '20 - 100 - 10', '2-10-2', '60-20-10' <html>");
        panel2.add(agentNumberTextField, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panel2.add(separator2, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), null, null, 0, false));
        groupTabbedPane = new JTabbedPane();
        panel2.add(groupTabbedPane, new GridConstraints(14, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(600, 200), null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, -1, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Gruppen");
        panel2.add(label3, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 300), null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Beschreibung: ");
        label4.setToolTipText("optional, kann leer gelassen werden (max 300 Zeichen)");
        panel2.add(label4, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionTextPane = new JTextPane();
        descriptionTextPane.setToolTipText("optional, kann leer gelassen werden (max 300 Zeichen)");
        panel2.add(descriptionTextPane, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 50), null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        addGroupButton.setText("Gruppe hinzufügen");
        panel2.add(addGroupButton, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JFormattedTextField();
        nameTextField.setToolTipText("Erlaubt sind Groß-, Kleinbuchstaben und Zahlen, Beispiele: 'Gefangenendilemma', 'MeinSpiel'");
        panel2.add(nameTextField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAgentGroupRadioButton.setSelected(true);
        idAgentGroupRadioButton.setText("Wähle Agenten nach ihren IDs");
        panel2.add(idAgentGroupRadioButton, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        percentageAgentGroupRadioButton.setText("Wähle Agenten nach prozentualem Anteil");
        panel2.add(percentageAgentGroupRadioButton, new GridConstraints(13, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useCapitalCheckBox = new JCheckBox();
        useCapitalCheckBox.setText("Startkapital nur für Strategiebestimmung nutzen");
        panel2.add(useCapitalCheckBox, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 3, new Insets(10, 10, 10, 10), -1, -1));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("Fertig");
        panel3.add(finishButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel3.add(spacer7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        panel3.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

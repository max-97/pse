package de.sswis.view;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.CustomComponents.ParameterTable;
import de.sswis.view.model.VMCombinedStrategy;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer kombinierten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewCombinedStrategyView implements AbstractNewCombinedStrategyView {

    private JFrame frame;

    private final int MAX_LINES = 10;

    private VMCombinedStrategy vmCombinedStrategy = new VMCombinedStrategy();

    private List<String> conditions = new ArrayList<>();
    private List<String> baseStrategies = new ArrayList<>();

    private HashMap<String, String[]> conditionParameters;

    private JPanel MainPanel;

    private List<JLabel> priorityLabels;
    private List<JComboBox> conditionComboBoxes = new ArrayList<>();

    private List<ParameterTable> additionalParameterLists = new ArrayList<>();
    private List<JComboBox> strategyComboBoxes = new ArrayList<>();

    private JPanel ContentPane;
    private JButton finishButton;
    private JButton cancelButton;
    private JPanel panel1 = new JPanel();
    private JTextPane descriptionTextPane;
    private JFormattedTextField nameTextField;
    private JComboBox defaultStrategy;
    private JButton addConditionButton;
    private JButton removeLastButton;
    private JLabel prioTitleLabel;
    private JLabel conditionTitleLabel;
    private JLabel strategyTitleLabel;
    private JPanel conditionPanel;

    private AbstractManageCombinedStrategiesView parentView;

    public NewCombinedStrategyView() {
        vmCombinedStrategy = new VMCombinedStrategy();
        conditions = new ArrayList<String>();
        baseStrategies = new ArrayList<String>();
        priorityLabels = new ArrayList<JLabel>();
        conditionComboBoxes = new ArrayList<JComboBox>();
        strategyComboBoxes = new ArrayList<JComboBox>();
        additionalParameterLists = new ArrayList<ParameterTable>();
        conditionParameters = new HashMap<>();
    }

    private void addNewLine() {
        JLabel prio = new JLabel((priorityLabels.size() + 1) + ".");
        priorityLabels.add(prio);

        JComboBox condition = new JComboBox(conditions.toArray());

        conditionComboBoxes.add(condition);

        ParameterTable param = new ParameterTable(new ArrayList<String>());
        additionalParameterLists.add(param);

        JComboBox strategy = new JComboBox(baseStrategies.toArray());

        strategyComboBoxes.add(strategy);

        //add to panel

        int index = conditionComboBoxes.size() - 1;

        conditionPanel.add(conditionComboBoxes.get(index),
                new GridConstraints(1 + index * 2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        conditionPanel.add(priorityLabels.get(index),
                new GridConstraints(1 + index * 2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        conditionPanel.add(strategyComboBoxes.get(index),
                new GridConstraints(1 + index * 2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        //conditionPanel.add(additionalParameterLists.get(index).$$$getRootComponent$$$(),
        //        new GridConstraints(2 + index * 2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        removeLastButton.setEnabled(true);

        addConditionButton.setEnabled(!(conditionComboBoxes.size() >= MAX_LINES));

        update();
    }

    private void removeLastLine() {
        int index = conditionComboBoxes.size() - 1;

        conditionPanel.remove(conditionComboBoxes.remove(index));
        conditionPanel.remove(strategyComboBoxes.remove(index));
        conditionPanel.remove(priorityLabels.remove(index));
        //conditionPanel.remove(additionalParameterLists.remove(index).$$$getRootComponent$$$());

        removeLastButton.setEnabled(!conditionComboBoxes.isEmpty());
        addConditionButton.setEnabled(!(conditionComboBoxes.size() >= MAX_LINES));
        update();
    }

    @Override
    public void update() {
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void updateVM() {
        vmCombinedStrategy = new VMCombinedStrategy();

        vmCombinedStrategy.setName(nameTextField.getText());

        for (int i = 0; i < conditionComboBoxes.size(); i++) {
            vmCombinedStrategy.addStrategy((String) strategyComboBoxes.get(i).getSelectedItem(),
                    (String) conditionComboBoxes.get(i).getSelectedItem());
        }

        vmCombinedStrategy.setDefaultStrategy((String) defaultStrategy.getSelectedItem());

        vmCombinedStrategy.setDescription(descriptionTextPane.getText());
    }

    @Override
    public void show() {
        frame = new JFrame("Kombinierte Strategie Bearbeiten");
        frame.setContentPane(this.MainPanel);
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
    public VMCombinedStrategy getCombinedStrategy() {
        updateVM();
        return this.vmCombinedStrategy;
    }

    @Override
    public void setCombinedStrategy(VMCombinedStrategy combinedStrategy) {

        this.vmCombinedStrategy = combinedStrategy;
        nameTextField.setText(vmCombinedStrategy.getName());
        descriptionTextPane.setText(vmCombinedStrategy.getDescription());
        for (int i = 0; i < vmCombinedStrategy.getConditions().size() - 1; i++) {
            addNewLine();
            //TODO: do for additional Parameters
            //TODO: VM check for correct Order!
            conditionComboBoxes.get(i).setSelectedItem(vmCombinedStrategy.getConditions().get(i));
            strategyComboBoxes.get(i).setSelectedItem(vmCombinedStrategy.getStrategies().get(i));
        }
    }

    @Override
    public void addCondition(String name) {
        conditions.add(name);
    }

    @Override
    public void addBaseStrategy(String name) {

        baseStrategies.add(name);
        defaultStrategy.addItem(name);

    }

    @Override
    public void addParameters(HashMap<String, String[]> parameters) {
        this.conditionParameters.putAll(parameters);
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageCombinedStrategiesView) parentView;
    }

    @Override
    public AbstractManageCombinedStrategiesView getParentView() {
        return this.parentView;
    }


    private void createUIComponents() {
        addConditionButton = new JButton();
        addConditionButton.addActionListener(e -> addNewLine());

        removeLastButton = new JButton();
        removeLastButton.addActionListener(e -> removeLastLine());

        defaultStrategy = new JComboBox(baseStrategies.toArray());

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
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ContentPane = new JPanel();
        ContentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        MainPanel.add(ContentPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        ContentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1.setLayout(new GridLayoutManager(10, 4, new Insets(20, 20, 20, 20), -1, -1));
        scrollPane1.setViewportView(panel1);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Beschreibung:");
        panel1.add(label1, new GridConstraints(8, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionTextPane = new JTextPane();
        panel1.add(descriptionTextPane, new GridConstraints(9, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Name: ");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JFormattedTextField();
        panel1.add(nameTextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        panel1.add(defaultStrategy, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addConditionButton.setText("Bedingung hinzufügen");
        panel1.add(addConditionButton, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Keine Bedingung: ");
        panel1.add(label3, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeLastButton.setEnabled(false);
        removeLastButton.setText("letzte Bedingung entfernen");
        panel1.add(removeLastButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        conditionPanel = new JPanel();
        conditionPanel.setLayout(new GridLayoutManager(21, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(conditionPanel, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        prioTitleLabel = new JLabel();
        Font prioTitleLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, prioTitleLabel.getFont());
        if (prioTitleLabelFont != null) prioTitleLabel.setFont(prioTitleLabelFont);
        prioTitleLabel.setText("Priorität");
        conditionPanel.add(prioTitleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(94, 18), null, 0, false));
        conditionTitleLabel = new JLabel();
        Font conditionTitleLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, conditionTitleLabel.getFont());
        if (conditionTitleLabelFont != null) conditionTitleLabel.setFont(conditionTitleLabelFont);
        conditionTitleLabel.setText("Bedingungen");
        conditionPanel.add(conditionTitleLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        strategyTitleLabel = new JLabel();
        Font strategyTitleLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, strategyTitleLabel.getFont());
        if (strategyTitleLabelFont != null) strategyTitleLabel.setFont(strategyTitleLabelFont);
        strategyTitleLabel.setText("Strategie");
        conditionPanel.add(strategyTitleLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 50), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        ContentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("fertig");
        panel2.add(finishButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        panel2.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return MainPanel;
    }
}

package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMCombinedStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Tab fuer eine kombinierte Strategie, der in der ManageCombinedStrategiesView angezeigt wird.
 */
public class CombinedStrategyTab {

    private VMCombinedStrategy combinedStrategy;

    private JPanel MainPanel;
    private JTextPane descriptionTextPane;
    private JButton deleteButton;
    private JButton editButton;
    private JLabel nameLabel;

    private List<JLabel> priorityLabels;
    private List<JLabel> conditionLabels;
    private List<JLabel> strategyLabels;

    private JPanel panel1 = new JPanel();

    /**
     * Erstellt ein neues Tab für die kombinierte Strategie.
     */
    public CombinedStrategyTab() {

        priorityLabels = new ArrayList<JLabel>();
        conditionLabels = new ArrayList<JLabel>();
        strategyLabels = new ArrayList<JLabel>();
        $$$setupUI$$$();
    }

    public void setVMCombinedStrategy(VMCombinedStrategy vmCombinedStrategy) {
        this.combinedStrategy = vmCombinedStrategy;

        nameLabel.setText(combinedStrategy.getName());
        descriptionTextPane.setText(combinedStrategy.getDescription());

        addConditionLines();

    }

    private void addConditionLines() {
        for (int i = 0; i < combinedStrategy.getConditions().size(); i++) {

            //erstelle Komponenten
            priorityLabels.add(new JLabel((i + 1) + "."));
            conditionLabels.add(new JLabel(combinedStrategy.getConditions().get(i)));
            strategyLabels.add(new JLabel(combinedStrategy.getStrategies().get(i)));

            //zum Panel hinzufügen
            panel1.add(priorityLabels.get(i),
                    new GridConstraints(3 + i, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            panel1.add(conditionLabels.get(i),
                    new GridConstraints(3 + i, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            panel1.add(strategyLabels.get(i),
                    new GridConstraints(3 + i, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        }

    }


    public VMCombinedStrategy getCombinedStrategy() {
        return combinedStrategy;
    }

    public void addEditButtonActionlistener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addDeleteButtonActionlistener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }


    private void createUIComponents() {

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
        MainPanel.setEnabled(false);
        panel1.setLayout(new GridLayoutManager(34, 5, new Insets(10, 10, 10, 10), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(30, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Beschreibung:");
        panel1.add(label1, new GridConstraints(31, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionTextPane = new JTextPane();
        descriptionTextPane.setEditable(false);
        panel1.add(descriptionTextPane, new GridConstraints(32, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("kombinierte Strategie löschen");
        panel1.add(deleteButton, new GridConstraints(33, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Bedingungen");
        panel1.add(label2, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Strategie");
        panel1.add(label3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Priorität");
        panel1.add(label4, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(29, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("kombinierte Strategie bearbeiten");
        panel1.add(editButton, new GridConstraints(33, 0, 1, 3, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(25, -1), null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("");
        panel1.add(nameLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

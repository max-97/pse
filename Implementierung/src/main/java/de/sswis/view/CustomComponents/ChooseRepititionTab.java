package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Tab Fenster zu einer Konfiguration im ShowCompareView.
 * <p>
 * Dient zum Waehlen welche Wiederholung für die jeweilige Konfiguration angezeigt wird.
 */
public class ChooseRepititionTab {
    private String tabTitle;

    private JPanel MainPanel;
    private JFormattedTextField repititionTextField;
    private JRadioButton averageRadioButton;
    private JRadioButton repititionRadioButton;
    private JLabel kLabel;
    private ButtonGroup buttonGroup;


    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public ChooseRepititionTab(String tabTitle) {
        this.tabTitle = tabTitle;

    }

    public String getTabTitle() {
        return tabTitle;
    }

    public boolean isAverageSelected () {
        return averageRadioButton.isSelected();
    }

    public int getRepitionNumber() {
        return (int) repititionTextField.getValue();
    }

    public void update () {

        kLabel.setEnabled(repititionRadioButton.isSelected());
        repititionTextField.setEnabled(repititionRadioButton.isSelected());

    }

    private void createUIComponents() {

        buttonGroup = new ButtonGroup();
        averageRadioButton = new JRadioButton();
        buttonGroup.add(averageRadioButton);

        repititionRadioButton = new JRadioButton();
        buttonGroup.add(repititionRadioButton);

        repititionTextField = new JFormattedTextField(NumberFormat.getNumberInstance());

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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        averageRadioButton.setEnabled(false);
        averageRadioButton.setSelected(true);
        averageRadioButton.setText("Zeige Durchschnitt aller Wiederholungen");
        panel1.add(averageRadioButton, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        repititionRadioButton = new JRadioButton();
        repititionRadioButton.setEnabled(false);
        repititionRadioButton.setText("Zeige die k'te Wiederholung");
        panel1.add(repititionRadioButton, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setEnabled(false);
        label1.setText("k = ");
        panel1.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repititionTextField = new JFormattedTextField();
        repititionTextField.setEnabled(false);
        panel1.add(repititionTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Panel, das eine Tabelle bestehend aus festgelegtem Text Labels und Text Eingabe Feldern.
 */
public class ParameterTable {

    private List<JLabel> parameterLabels = new ArrayList<>();
    private List<JTextField> parameterTextFields = new ArrayList<>();

    private JPanel MainPanel;
    private JLabel additionalParameterLabel;


    public ParameterTable() {

        $$$setupUI$$$();

    }

    public void setParameters(String[] parameterNames) {

        //entferne alle Komponenten
        for (int i = 0; i < parameterLabels.size(); i++) {
            MainPanel.remove(parameterLabels.remove(i));
            MainPanel.remove(parameterTextFields.remove(i));
        }

        //füge die neuen Komponenten hinzu
        for (int i = 0; i < parameterNames.length; i++) {
            JLabel label = new JLabel(parameterNames[i]);
            parameterLabels.add(label);

            JTextField textField = new JTextField();
            parameterTextFields.add(textField);

            MainPanel.add(label,
                    new GridConstraints(1 + i, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

            MainPanel.add(textField,
                    new GridConstraints(1 + i, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

        }
    }

    public void setParameterValues(String[] values) {
        if (values.length == parameterTextFields.size()) {
            for (int i = 0; i < values.length; i++) {
                parameterTextFields.get(i).setText(values[i]);
            }
        }

    }

    public Object returnString() {
        return "Hallo";
    }

    public HashMap<String, Object> getAllUserInputs() {
        HashMap<String, Object> params = new HashMap<>();

        for (int i = 0; i < parameterTextFields.size(); i++) {
            params.put(parameterLabels.get(i).getText(), parameterTextFields.get(i).getText());

        }
        return params;
    }

    private void createUIComponents() {
        additionalParameterLabel = new JLabel();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(20, 2, new Insets(0, 0, 0, 0), -1, -1));
        additionalParameterLabel = new JLabel();
        additionalParameterLabel.setText("zusätzliche Parameter:");
        MainPanel.add(additionalParameterLabel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

package de.sswis.view.CustomComponents;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class CombinedStrategyTab {
    private JTextField textField1;
    private JPanel MainPanel;

    public void preview() {
        //TODO: remove me!!
        JFrame frame = new JFrame("CombinedStrategyTab");
        frame.setContentPane(new CombinedStrategyTab().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        addLine();
        frame.pack();
    }

    private void addLine() {

        int n = 1;
        JLabel label = new JLabel();
        label.setEnabled(true);
        label.setText("Label");
        MainPanel.add(label, new GridConstraints(n, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(192, 5), new Dimension(-1, 5), 0, false));
        JTextField textField = new JTextField();
        MainPanel.add(textField, new GridConstraints(n, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));


    }

}

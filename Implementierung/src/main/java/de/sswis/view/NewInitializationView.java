package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMInitialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer Initialisierung.
 *
 * @author Sophie Bräuniger
 */
public class NewInitializationView implements AbstractNewInitializationView {

    private VMInitialization vmInitialization;

    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField2;
    private JTabbedPane tabbedPane2;
    private JTextField textField3;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JFormattedTextField formattedTextField6;
    private JFormattedTextField formattedTextField7;
    private JTabbedPane tabbedPane3;
    private JFormattedTextField formattedTextField1;
    private JButton button5;
    private JButton button6;

    @Override
    public void update() {

    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {

    }

}

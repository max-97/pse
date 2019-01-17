package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public class NewConfigurationView implements AbstractNewConfigurationView {

    private VMConfiguration vmConfiguration;

    private List<String> adaptationAlgs;
    private List<String> pairingAlgs;
    private List<String> rankingAlgs;
    private List<VMGame> games;
    private List<VMInitialization> inits;
    private List<VMStrategy> strategies;
    private List<VMCombinedStrategy> cStrategies;


    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton fertigButton;
    private JButton abbrechenButton;


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

package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ein Fenster zum Verwalten von gemischten Strategien.
 *
 * @author Sophie Bräuniger
 */
public class ManageStrategiesView implements AbstractManageStrategiesView {
    private JButton aenderungenSpeichernUndSchließenButton;
    private JButton abbrechenButton;
    private JTextPane textPane1;
    private JButton kombinierteStrategieLoeschenButton;
    private JButton neueKombinierteStrategieButton;
    private JButton kombinierteStrategieBearbeitenButton;

    @Override
    public void addStrategy(VMStrategy vmStrategy) {

    }

    @Override
    public void removeStrategy(String strategyName) {

    }

    @Override
    public void addNewMixedStrategyButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addEditMixedStrategyButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addDeleteMixedStrategyButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void update() {

    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

}

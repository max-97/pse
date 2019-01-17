package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMConfiguration;

import javax.swing.*;
import java.awt.*;

/**
 * Ein Fenster zum Anzeigen von Ergebnissen einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public class ShowResultView implements AbstractShowResultView {

    private VMConfiguration vmConfiguration;


    private JButton vergleichenMitButton;
    private JRadioButton zeigeDurchschnittAllerWiederholungenRadioButton;
    private JRadioButton zeigeDieKTeRadioButton;
    private JFormattedTextField formattedTextField1;
    private JSlider slider1;

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

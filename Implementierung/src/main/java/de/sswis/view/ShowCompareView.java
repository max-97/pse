package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Vergleichen von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public class ShowCompareView implements AbstractShowCompareView {

    private List<VMConfiguration> configurations;


    private JTree waehlenSieEineKonfigurationTree;
    private JRadioButton waehleFuerAlleKonfigurationenRadioButton;
    private JRadioButton zeigeDieKTeRadioButton;
    private JFormattedTextField formattedTextField1;
    private JTabbedPane tabbedPane1;

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
    public void addCompareButtonActionlistener(ActionListener listener) {

    }

}

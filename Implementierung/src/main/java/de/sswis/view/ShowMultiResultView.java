package de.sswis.view;

import com.intellij.uiDesigner.core.GridLayoutManager;
import de.sswis.view.model.VMConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Anzeigen von Ergebnissen einer Mehrfachkonfiguration.
 *
 * @author Sophie Bräuniger
 */
public class ShowMultiResultView implements AbstractShowMultiResultView {

    private JFrame frame;

    private List<VMConfiguration> vmConfigurations;
    private JPanel MainPanel;



    @Override
    public void update() {

    }

    @Override
    public void show() {
        frame = new JFrame("Ergebnisse Mehrfachkonfiguration");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void close() {

    }

    @Override
    public void addCompareButtonActionlistener(ActionListener listener) {

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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}

package de.sswis.view;

import com.intellij.uiDesigner.core.GridLayoutManager;
import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten eines Stufenspiels.
 *
 * @author Sophie Bräuniger
 */
public class NewGameView implements AbstractNewGameView {

    private JFrame frame;


    private VMGame vmGame;
    private JPanel MainPanel;


    @Override
    public void update() {

    }

    @Override
    public void show() {
        frame = new JFrame("Stufenspiel Bearbeiten");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

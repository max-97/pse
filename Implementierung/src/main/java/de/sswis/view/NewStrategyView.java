package de.sswis.view;


import com.intellij.uiDesigner.core.GridLayoutManager;
import de.sswis.view.model.VMStrategy;

import java.awt.event.ActionListener;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer gemischten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewStrategyView implements AbstractNewStrategyView {
    private VMStrategy vmStrategy;

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
        final javax.swing.JPanel panel1 = new javax.swing.JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
    }
}

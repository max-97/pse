package de.sswis.view;

import com.intellij.uiDesigner.core.GridLayoutManager;
import de.sswis.view.model.VMCombinedStrategy;
import de.sswis.view.model.VMStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von kombinierten Strategien.
 *
 * @author Sophie Bräuniger
 */
public class ManageCombinedStrategiesView implements AbstractManageCombinedStrategiesView {
    private List<VMCombinedStrategy> vmCombinedStrategies;

    @Override
    public void addStrategy(VMCombinedStrategy vmStrategy) {

    }

    @Override
    public void removeStrategy(String strategyName) {

    }

    @Override
    public void addNewStrategyButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addEditStrategyButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addDeleteStrategyButtonActionlistener(ActionListener listener) {

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

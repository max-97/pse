package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.CustomComponents.MixedStrategyTab;
import de.sswis.view.model.VMResult;
import de.sswis.view.model.VMStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Ein Fenster zum Verwalten von gemischten Strategien.
 *
 * @author Sophie Bräuniger
 */
public class ManageStrategiesView implements AbstractManageStrategiesView {

    private JFrame frame = new JFrame();
    ;

    private List<VMStrategy> vmStrategies;
    private List<MixedStrategyTab> strategyTabs;

    ActionListener editListener;
    ActionListener deleteListener;

    private JButton saveAndQuitButton;
    private JButton cancelButton;
    private JButton newStrategyButton;
    private JPanel MainPanel;
    private JTabbedPane strategiesPane;

    private AbstractMainView parentView;
    private VMStrategy editedStrategy;

    public ManageStrategiesView() {
        vmStrategies = new ArrayList<VMStrategy>();
        strategyTabs = new ArrayList<MixedStrategyTab>();
    }

    @Override
    public void addStrategy(VMStrategy vmStrategy) {
        vmStrategies.add(vmStrategy);
        MixedStrategyTab tab = new MixedStrategyTab();
        tab.setVmStrategy(vmStrategy);
        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);

        strategyTabs.add(tab);
        strategiesPane.addTab(vmStrategy.getName(), tab.$$$getRootComponent$$$());

        update();
    }

    @Override
    public void replaceStrategy(VMStrategy newStrategy) {
        int index = this.strategiesPane.getSelectedIndex();
        vmStrategies.remove(index);
        vmStrategies.add(index, newStrategy);

        MixedStrategyTab tab = new MixedStrategyTab();
        tab.setVmStrategy(newStrategy);
        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);

        strategyTabs.remove(index);
        strategyTabs.add(index, tab);
        strategiesPane.remove(index);
        strategiesPane.insertTab(newStrategy.getName(), null, tab.$$$getRootComponent$$$(), null, index);

        update();
    }

    @Override
    public void removeStrategy(String strategyName) {
        for (int i = 0; i < vmStrategies.size(); i++) {
            if (vmStrategies.get(i).getName().equals(strategyName)) {
                vmStrategies.remove(i);
                strategiesPane.removeTabAt(i);
                break;
            }
        }

        update();
    }

    @Override
    public void addNewMixedStrategyButtonActionlistener(ActionListener listener) {
        newStrategyButton.addActionListener(listener);
    }

    @Override
    public void addEditMixedStrategyButtonActionlistener(ActionListener listener) {
        editListener = listener;
    }

    @Override
    public void addDeleteMixedStrategyButtonActionlistener(ActionListener listener) {
        deleteListener = listener;
    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {
        saveAndQuitButton.addActionListener(listener);
    }

    @Override
    public VMStrategy getSelectedVM() {
        return vmStrategies.get(strategiesPane.getSelectedIndex());
    }

    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
        frame = new JFrame("Gemischte Strategien Verwaltung");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }

    @Override
    public void setEditedStrategy(VMStrategy strategy) {
        this.editedStrategy = strategy;
    }

    @Override
    public VMStrategy getEditedStrategy() {
        return this.editedStrategy;
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(5, 5, 5, 5), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Änderungen speichern und schließen");
        panel2.add(saveAndQuitButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        panel2.add(cancelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        strategiesPane = new JTabbedPane();
        strategiesPane.setTabPlacement(2);
        panel1.add(strategiesPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        newStrategyButton = new JButton();
        newStrategyButton.setText("neue gemischte Strategie");
        panel1.add(newStrategyButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

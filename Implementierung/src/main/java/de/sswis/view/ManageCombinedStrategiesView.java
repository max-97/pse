package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.CustomComponents.CombinedStrategyTab;
import de.sswis.view.model.VMCombinedStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von kombinierten Strategien.
 *
 * @author Sophie Bräuniger
 */
public class ManageCombinedStrategiesView implements AbstractManageCombinedStrategiesView {

    private JFrame frame = new JFrame();
    ;

    private List<VMCombinedStrategy> vmCombinedStrategies;

    private List<CombinedStrategyTab> strategyTabs;

    ActionListener editListener;
    ActionListener deleteListener;

    private JPanel ButtonPanel;
    private JButton saveAndQuitButton;
    private JTabbedPane StrategiesPane;
    private JButton newStrategyButton;
    private JPanel MainPanel;


    private AbstractMainView parentView;
    private VMCombinedStrategy editedCombinedStrategy;

    public ManageCombinedStrategiesView() {
        vmCombinedStrategies = new ArrayList<VMCombinedStrategy>();
        strategyTabs = new ArrayList<CombinedStrategyTab>();

    }

    @Override
    public void addStrategy(VMCombinedStrategy vmStrategy) {
        vmCombinedStrategies.add(vmStrategy);
        CombinedStrategyTab tab = new CombinedStrategyTab();
        tab.setVMCombinedStrategy(vmStrategy);

        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);
        strategyTabs.add(tab);
        StrategiesPane.addTab(vmStrategy.getName(), tab.$$$getRootComponent$$$());

        update();
    }

    @Override
    public void replaceCombinedStrategy(VMCombinedStrategy newCombinedStrategy) {
        int index = StrategiesPane.getSelectedIndex();
        vmCombinedStrategies.remove(index);
        vmCombinedStrategies.add(index, newCombinedStrategy);

        CombinedStrategyTab tab = new CombinedStrategyTab();
        tab.setVMCombinedStrategy(newCombinedStrategy);
        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);

        strategyTabs.remove(index);
        strategyTabs.add(index, tab);
        StrategiesPane.remove(index);
        StrategiesPane.insertTab(newCombinedStrategy.getName(), null, tab.$$$getRootComponent$$$(), null, index);

        update();
    }

    @Override
    public void removeStrategy(String strategyName) {
        UIManager.put("OptionPane.yesButtonText", "Ja");
        UIManager.put("OptionPane.noButtonText", "Nein");
        int n = JOptionPane.showConfirmDialog(null, "Wollen Sie die kombinierte Strategie löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < vmCombinedStrategies.size(); i++) {
                if (vmCombinedStrategies.get(i).getName().equals(strategyName)) {
                    vmCombinedStrategies.remove(i);
                    StrategiesPane.removeTabAt(i);
                    strategyTabs.remove(i);
                    break;
                }
            }

            update();
        } else if (n == JOptionPane.NO_OPTION) {
            close();
        }
    }

    @Override
    public void addNewStrategyButtonActionlistener(ActionListener listener) {
        newStrategyButton.addActionListener(listener);

    }

    @Override
    public void addEditStrategyButtonActionlistener(ActionListener listener) {
        editListener = listener;

    }

    @Override
    public void addDeleteStrategyButtonActionlistener(ActionListener listener) {
        deleteListener = listener;

    }

    @Override
    public void addCloseActionListener(ActionListener listener) {
        saveAndQuitButton.addActionListener(listener);
    }

    @Override
    public VMCombinedStrategy getSelectedVM() {

        return vmCombinedStrategies.get(StrategiesPane.getSelectedIndex());
    }

    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
        frame = new JFrame("Kombinierte Strategien Verwaltung");
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
    public void setEditedCombinedStrategy(VMCombinedStrategy vmCombinedStrategy) {
        this.editedCombinedStrategy = vmCombinedStrategy;
    }

    @Override
    public VMCombinedStrategy getEditedCombinedStrategy() {
        return this.editedCombinedStrategy;
    }


    private void createUIComponents() {
        StrategiesPane = new JTabbedPane();
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
        createUIComponents();
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(5, 5, 5, 5), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(ButtonPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Schließen");
        ButtonPanel.add(saveAndQuitButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        StrategiesPane.setTabPlacement(2);
        panel1.add(StrategiesPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        newStrategyButton = new JButton();
        newStrategyButton.setText("Neue kombinierte Strategie");
        panel1.add(newStrategyButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

}

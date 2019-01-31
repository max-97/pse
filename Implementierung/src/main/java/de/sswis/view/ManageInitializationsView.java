package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.AbstractManageInitializationsView;
import de.sswis.view.CustomComponents.InitializationTab;
import de.sswis.view.model.VMInitialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von Initialisierungen.
 *
 * @author Sophie Bräuniger
 */
public class ManageInitializationsView implements AbstractManageInitializationsView {

    private JFrame frame;

    private List<VMInitialization> vmInits;
    private List<InitializationTab> initializationTabs;

    private JPanel ButtonPanel;
    private JButton saveAndQuitButton;
    private JButton cancelButton;
    private JTabbedPane InitsPane;
    private JButton newInitButton;
    private JPanel MainPanel;

    private AbstractMainView parentView;

    public ManageInitializationsView() {
        vmInits = new ArrayList<VMInitialization>();
        initializationTabs = new ArrayList<InitializationTab>();
    }

    @Override
    public void addInit(VMInitialization vmInitialization) {
        vmInits.add(vmInitialization);
        addTab(vmInitialization);
    }

    private void addTab(VMInitialization initialization) {
        InitializationTab tab = new InitializationTab(initialization);
        InitsPane.addTab(initialization.getName(), tab.$$$getRootComponent$$$());
    }

    @Override
    public void removeInit(String initName) {
        for (int i = 0; i < vmInits.size(); i++) {
            if (vmInits.get(i).getName().equals(initName)) {
                vmInits.remove(i);
                InitsPane.removeTabAt(i);
                break;
            }
        }

    }

    @Override
    public void addNewInitButtonActionlistener(ActionListener listener) {
        newInitButton.addActionListener(listener);
    }

    @Override
    public void addEditInitButtonActionlistener(ActionListener listener) {
        for (int i = 0; i < initializationTabs.size(); i++) {
            initializationTabs.get(i).addEditButtonActionlistener(listener);
        }
    }

    @Override
    public void addDeleteInitButtonActionlistener(ActionListener listener) {
        for (int i = 0; i < initializationTabs.size(); i++) {
            initializationTabs.get(i).addDeleteButtonActionlistener(listener);
        }
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
    public VMInitialization getSelectedVM() {
        return vmInits.get(InitsPane.getSelectedIndex());
    }

    @Override
    public void update() {

    }

    @Override
    public void show() {
        frame = new JFrame("Initialisierungen Verwaltung");
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
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(ButtonPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Änderungen speichern und schließen");
        ButtonPanel.add(saveAndQuitButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        ButtonPanel.add(cancelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        InitsPane = new JTabbedPane();
        InitsPane.setTabPlacement(2);
        panel1.add(InitsPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        newInitButton = new JButton();
        newInitButton.setText("Neue Initialisierung");
        panel1.add(newInitButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

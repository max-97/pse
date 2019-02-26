package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.AbstractManageConfigurationsView;
import de.sswis.view.CustomComponents.ConfigurationTab;
import de.sswis.view.model.VMConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von Konfigurationen.
 *
 * @author Sophie Bräuniger
 */
public class ManageConfigurationsView implements AbstractManageConfigurationsView {

    private JFrame frame = new JFrame();
    ;

    private List<VMConfiguration> vmConfigurations;

    private List<ConfigurationTab> configTabs;

    ActionListener editListener;
    ActionListener deleteListener;

    private JPanel ButtonPanel;
    private JButton saveAndQuitButton;
    private JTabbedPane ConfigurationsPane;
    private JButton newConfigButton;
    private JPanel MainPanel;

    private AbstractMainView parentView;
    private VMConfiguration editedConfiguration;

    public ManageConfigurationsView() {
        vmConfigurations = new ArrayList<VMConfiguration>();
        configTabs = new ArrayList<ConfigurationTab>();
    }

    @Override
    public void addConfiguration(VMConfiguration configuration) {
        vmConfigurations.add(configuration);

        ConfigurationTab tab = new ConfigurationTab();
        tab.setVmConfiguration(configuration);

        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);
        configTabs.add(tab);
        ConfigurationsPane.addTab(configuration.getName(), tab.$$$getRootComponent$$$());

        update();
    }

    @Override
    public void replaceConfiguration(VMConfiguration newConfiguration) {
        int index = this.ConfigurationsPane.getSelectedIndex();
        vmConfigurations.remove(index);
        vmConfigurations.add(index, newConfiguration);

        ConfigurationTab tab = new ConfigurationTab();
        tab.setVmConfiguration(newConfiguration);
        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);

        configTabs.remove(index);
        configTabs.add(index, tab);
        ConfigurationsPane.remove(index);
        ConfigurationsPane.insertTab(newConfiguration.getName(), null, tab.$$$getRootComponent$$$(), null, index);

        update();
    }


    @Override
    public void removeConfiguration(String configName) {
        UIManager.put("OptionPane.yesButtonText", "Ja");
        UIManager.put("OptionPane.noButtonText", "Nein");
        int n = JOptionPane.showConfirmDialog(null, "Wollen Sie die Konfiguration löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < vmConfigurations.size(); i++) {
                if (vmConfigurations.get(i).getName().equals(configName)) {
                    vmConfigurations.remove(i);
                    configTabs.get(i);
                    ConfigurationsPane.removeTabAt(i);
                    break;
                }
            }

            update();
        } else if (n == JOptionPane.NO_OPTION) {
            close();
        }
    }

    @Override
    public void addNewConfigurationButtonActionlistener(ActionListener listener) {
        newConfigButton.addActionListener(listener);
    }

    @Override
    public void addEditConfigurationButtonActionlistener(ActionListener listener) {
        editListener = listener;
    }

    @Override
    public void addDeleteConfigurationButtonActionlistener(ActionListener listener) {
        deleteListener = listener;
    }

    @Override
    public void addCloseActionListener(ActionListener listener) {
        saveAndQuitButton.addActionListener(listener);
    }

    @Override
    public VMConfiguration getSelectedVM() {
        return vmConfigurations.get(ConfigurationsPane.getSelectedIndex());
    }

    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
        frame = new JFrame("Konfigurationen Verwaltung");
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
    public void setEditedConfiguration(VMConfiguration vmConfiguration) {
        this.editedConfiguration = vmConfiguration;
    }

    @Override
    public VMConfiguration getEditedConfiguration() {
        return this.editedConfiguration;
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
        ButtonPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(ButtonPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Schließen");
        ButtonPanel.add(saveAndQuitButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ConfigurationsPane = new JTabbedPane();
        ConfigurationsPane.setTabPlacement(2);
        panel1.add(ConfigurationsPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        newConfigButton = new JButton();
        newConfigButton.setText("Neue Konfiguration");
        panel1.add(newConfigButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

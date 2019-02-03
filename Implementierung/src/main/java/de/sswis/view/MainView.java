package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * in Hauptfenster der Benutzeroberfläche.
 *
 * @author Sophie Bräuniger
 */
public class MainView implements AbstractMainView {

    private JFrame frame;


    private List<VMConfiguration> configurations;
    private List<String> simulatingConfigs;
    private JMenuBar menuBar;

    private JMenuItem newGameItem;
    private JMenuItem newCombinedStrategyItem;
    private JMenuItem newMixedStrategyItem;
    private JMenuItem newInitiliazationItem;
    private JMenuItem newConfigurationItem;

    private JMenuItem manageGamesItem;
    private JMenuItem manageCombinedStrategiesItem;
    private JMenuItem manageMixedStrategiesItem;
    private JMenuItem manageInitiliazationsItem;
    private JMenuItem manageConfigurationsItem;
    private JMenuItem manageResultsItem;

    private JButton startButton;
    private JButton showResultButton;
    private JTree ConfigurationTree;
    private JButton saveResultButton;
    private JPanel TitelPanel;
    private JPanel ContentPanel;
    private JScrollPane ConfigurationScrollPane;
    private JPanel MainPanel;
    private JLabel ChooseLabel;
    private JLabel TitleLabel;
    private JButton stopButton;


    public MainView() {

        configurations = new ArrayList<VMConfiguration>();
        simulatingConfigs = new ArrayList<>();
        frame = new JFrame("SSWIS");

        $$$setupUI$$$();
        setMenuBar();
    }


    @Override
    public void addConfiguration(VMConfiguration configuration) {
        configurations.add(configuration);

        ConfigurationTree.setModel(createConfigurationTree());
    }

    @Override
    public void removeConfiguration(String configurationName) {
        for (int i = 0; i < configurations.size(); i++) {
            if (configurations.get(i).getName().equals(configurationName)) {
                configurations.remove(i);
                break;
            }
        }

        ConfigurationTree.setModel(createConfigurationTree());
    }

    @Override
    public void addResult(String NameConfiguration, VMResult result) {

        for (int i = 0; i < configurations.size(); i++) {
            if (configurations.get(i).getName().equals(NameConfiguration)) {
                configurations.get(i).setResult(result);
                break;
            }
        }
    }

    @Override
    public void setSimulationStarted(String NameConfiguration) {
        simulatingConfigs.add(NameConfiguration);
        updateButtons();
    }

    @Override
    public void setSimulationFinished(String NameConfiguration) {

        simulatingConfigs.remove(NameConfiguration);
        updateButtons();
    }


    @Override
    public VMConfiguration getSelected() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) ConfigurationTree.getLastSelectedPathComponent();
        if (node != null && node.isLeaf()) {
            for (int i = 0; i < configurations.size(); i++) {
                if (configurations.get(i).getName().equals(node.getUserObject())) {
                    return configurations.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public int askForRepitionNumber() {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));

        String[] options = {"Abbrechen", "Ok"};
        int option = JOptionPane.showOptionDialog(frame, spinner, "Gib die Anzahl der Wiederholungen ein", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Ok");

        if (option == 1) {
            return (int) spinner.getValue();
        }

        return -1;
    }

    @Override
    public void addStartButtonActionlistener(ActionListener listener) {
        startButton.addActionListener(listener);

    }

    @Override
    public void addStopActionListener(ActionListener listener) {
        stopButton.addActionListener(listener);
    }

    @Override
    public void addShowButtonActionlistener(ActionListener listener) {
        showResultButton.addActionListener(listener);
    }

    @Override
    public void addSaveButtonActionlistener(ActionListener listener) {
        saveResultButton.addActionListener(listener);
    }

    @Override
    public void addNewGameMenuActionListener(ActionListener listener) {
        newGameItem.addActionListener(listener);
    }

    @Override
    public void addNewCombiStrategyMenuActionListener(ActionListener listener) {
        newCombinedStrategyItem.addActionListener(listener);
    }

    @Override
    public void addNewStrategyMenuActionListener(ActionListener listener) {
        newMixedStrategyItem.addActionListener(listener);
    }

    @Override
    public void addNewInitMenuActionListener(ActionListener listener) {
        newInitiliazationItem.addActionListener(listener);
    }

    @Override
    public void addNewConfigMenuActionListener(ActionListener listener) {
        newConfigurationItem.addActionListener(listener);
    }

    @Override
    public void addManageGameMenuActionListener(ActionListener listener) {
        manageGamesItem.addActionListener(listener);
    }

    @Override
    public void addManageCombiStrategyMenuActionListener(ActionListener listener) {
        manageCombinedStrategiesItem.addActionListener(listener);
    }

    @Override
    public void addManageStrategyMenuActionListener(ActionListener listener) {
        manageMixedStrategiesItem.addActionListener(listener);
    }

    @Override
    public void addManageInitMenuActionListener(ActionListener listener) {
        manageInitiliazationsItem.addActionListener(listener);
    }

    @Override
    public void addManageConfigMenuActionListener(ActionListener listener) {
        manageConfigurationsItem.addActionListener(listener);
    }


    @Override
    public void addManageResultMenuActionListener(ActionListener listener) {
        manageResultsItem.addActionListener(listener);
    }


    @Override
    public Collection<VMResult> getResults() {
        Collection<VMResult> results = new ArrayList<>();
        for (VMConfiguration c : configurations) {
            results.add(c.getResult());
        }
        return results;
    }

    @Override
    public void update() {
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    private void updateButtons() {
        if (getSelected() != null) {
            if (simulatingConfigs.contains(getSelected().getName())) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
            } else {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
            }

            if (getSelected().hasResult()) {
                showResultButton.setEnabled(true);
                saveResultButton.setEnabled(true);
            } else {
            }

        } else {
            startButton.setEnabled(false);
            stopButton.setEnabled(false);
            showResultButton.setEnabled(false);
            saveResultButton.setEnabled(false);
        }
        update();
    }


    @Override
    public void show() {

        frame = new JFrame("SSWIS");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.pack();
        // Das muss immer nach pack() stehen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void close() {

    }

    @Override
    public void setParentView(AbstractView parentView) {

    }

    private void setMenuBar() {
        menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("Neu");
        newGameItem = new JMenuItem("Stufenspiel");
        newMenu.add(newGameItem);
        newCombinedStrategyItem = new JMenuItem("Kombinierte Strategie");
        newMenu.add(newCombinedStrategyItem);
        newMixedStrategyItem = new JMenuItem("Gemischte Strategie");
        newMenu.add(newMixedStrategyItem);
        newInitiliazationItem = new JMenuItem("Initialisierung");
        newMenu.add(newInitiliazationItem);
        newConfigurationItem = new JMenuItem("Konfiguration");
        newMenu.add(newConfigurationItem);

        menuBar.add(newMenu);

        JMenu manageMenu = new JMenu("Verwalten");
        manageGamesItem = new JMenuItem("Stufenspiele");
        manageMenu.add(manageGamesItem);
        manageCombinedStrategiesItem = new JMenuItem("Kombinierte Strategien");
        manageMenu.add(manageCombinedStrategiesItem);
        manageMixedStrategiesItem = new JMenuItem("Gemischte Strategien");
        manageMenu.add(manageMixedStrategiesItem);
        manageInitiliazationsItem = new JMenuItem("Initialisierungen");
        manageMenu.add(manageInitiliazationsItem);
        manageConfigurationsItem = new JMenuItem("Konfigurationen");
        manageMenu.add(manageConfigurationsItem);
        manageResultsItem = new JMenuItem("Ergebnisse");
        manageMenu.add(manageResultsItem);

        menuBar.add(manageMenu);

    }

    private TreeModel createConfigurationTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Konfigurationen");
        DefaultMutableTreeNode simple = new DefaultMutableTreeNode("Einfache Konfigurationen");
        DefaultMutableTreeNode multi = new DefaultMutableTreeNode("Mehrfache Konfigurationen");


        DefaultMutableTreeNode config;
        for (int i = 0; i < configurations.size(); i++) {
            config = new DefaultMutableTreeNode(configurations.get(i).getName());
            if (configurations.get(i).isMultiConfiguration()) {
                multi.add(config);
            } else {
                simple.add(config);
            }
        }

        root.add(simple);
        root.add(multi);

        TreeModel configTreeModel = new DefaultTreeModel(root);

        return configTreeModel;
    }

    private void createUIComponents() {
        ConfigurationTree = new JTree();
        ConfigurationTree.addTreeSelectionListener(e -> updateButtons());
        ConfigurationTree.setModel(createConfigurationTree());


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
        MainPanel.setLayout(new GridLayoutManager(3, 1, new Insets(20, 20, 20, 20), -1, -1));
        TitelPanel = new JPanel();
        TitelPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(TitelPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        TitleLabel = new JLabel();
        Font TitleLabelFont = this.$$$getFont$$$(null, -1, 22, TitleLabel.getFont());
        if (TitleLabelFont != null) TitleLabel.setFont(TitleLabelFont);
        TitleLabel.setText("SSWIS Simuliert WIederholte Spiele");
        TitelPanel.add(TitleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        TitelPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ContentPanel = new JPanel();
        ContentPanel.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(ContentPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ConfigurationScrollPane = new JScrollPane();
        ConfigurationScrollPane.setHorizontalScrollBarPolicy(31);
        ConfigurationScrollPane.setVerticalScrollBarPolicy(22);
        ContentPanel.add(ConfigurationScrollPane, new GridConstraints(1, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ConfigurationTree.setEditable(false);
        ConfigurationTree.setLargeModel(true);
        ConfigurationTree.setRootVisible(true);
        ConfigurationTree.setShowsRootHandles(true);
        ConfigurationTree.putClientProperty("JTree.lineStyle", "");
        ConfigurationScrollPane.setViewportView(ConfigurationTree);
        ChooseLabel = new JLabel();
        ChooseLabel.setText("Wählen Sie eine Konfiguration");
        ContentPanel.add(ChooseLabel, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        ContentPanel.add(spacer2, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        startButton = new JButton();
        startButton.setEnabled(false);
        startButton.setText("Simulationen starten");
        ContentPanel.add(startButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showResultButton = new JButton();
        showResultButton.setEnabled(false);
        showResultButton.setText("Ergebnisse anzeigen");
        ContentPanel.add(showResultButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveResultButton = new JButton();
        saveResultButton.setEnabled(false);
        saveResultButton.setText("Ergebisse speichern");
        ContentPanel.add(saveResultButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stopButton = new JButton();
        stopButton.setEnabled(false);
        stopButton.setText("Simulation stoppen");
        ContentPanel.add(stopButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        MainPanel.add(separator1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

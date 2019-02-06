package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.CustomComponents.ParameterTable;
import de.sswis.view.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public class NewConfigurationView implements AbstractNewConfigurationView {

    private JFrame frame = new JFrame();

    private AbstractManageConfigurationsView parentView;

    private VMConfiguration vmConfiguration = new VMConfiguration();

    private List<String> adaptationAlgs = new ArrayList<>();
    private List<String> pairingAlgs = new ArrayList<>();
    private List<String> rankingAlgs = new ArrayList<>();
    private List<String> games = new ArrayList<>();
    private List<String> inits = new ArrayList<>();
    private List<String> strategies = new ArrayList<>();
    private List<String> cStrategies = new ArrayList<>();

    private HashMap<String, String[]> algorithmParameters = new HashMap<>();

    private ParameterTable pairingParameterTable;
    private ParameterTable rankingParameterTable;
    private ParameterTable adaptionParameterTable;

    private JComboBox gameComboBox;
    private JComboBox initComboBox;
    private JComboBox pairingComboBox;
    private JComboBox rankingComboBox;
    private JComboBox adaptionComboBox;
    private JTextField adaptionProbabilityTextField;
    private JTextField roundsTextField;
    private JTextField cyclesTextField;
    private JButton finishButton;
    private JButton cancelButton;
    private JScrollPane pairingAlgoParamPane;
    private JPanel ContentPane;
    private JFormattedTextField nameTextField;
    private JPanel panel1;
    private JButton setPairingParamsButton;
    private JButton setRankinParamsButton;
    private JButton setAdaptionParamsButton;
    private JSpinner equilibriumRounds;
    private JSpinner equilibriumMaxChange;

    public NewConfigurationView() {

        $$$setupUI$$$();
        frame = new JFrame("NewConfigurationView");

        addParameterTables();

    }

    private void addParameterTables() {

        pairingParameterTable = new ParameterTable();
        rankingParameterTable = new ParameterTable();
        adaptionParameterTable = new ParameterTable();

        pairingComboBox.addActionListener(e -> setNewParameterTable(e.getSource()));
        rankingComboBox.addActionListener(e -> setNewParameterTable(e.getSource()));
        adaptionComboBox.addActionListener(e -> setNewParameterTable(e.getSource()));

        setPairingParamsButton.addActionListener(e -> askForParameter(pairingParameterTable, "Paarungsalgorithmus"));
        setRankinParamsButton.addActionListener(e -> askForParameter(rankingParameterTable, "Bewertungsalgorithmus"));
        setAdaptionParamsButton.addActionListener(e -> askForParameter(adaptionParameterTable, "Anpassungsalgorithmus"));

    }

    private void askForParameter(ParameterTable params, String title) {
        JOptionPane.showMessageDialog(frame, params.$$$getRootComponent$$$(), title, JOptionPane.QUESTION_MESSAGE);
    }

    @Override
    public void update() {

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void updateVM() {
        vmConfiguration.setName(nameTextField.getText());
        vmConfiguration.setGame((String) gameComboBox.getSelectedItem());
        vmConfiguration.setInit((String) initComboBox.getSelectedItem());
        vmConfiguration.setPairingAlg((String) pairingComboBox.getSelectedItem());
        vmConfiguration.setPairingParameters(pairingParameterTable.getAllUserInputs());

        vmConfiguration.setRankingAlg((String) rankingComboBox.getSelectedItem());
        vmConfiguration.setRankingParameters(rankingParameterTable.getAllUserInputs());

        vmConfiguration.setAdaptationAlg((String) adaptionComboBox.getSelectedItem());
        vmConfiguration.setAdaptationParameters(adaptionParameterTable.getAllUserInputs());

        vmConfiguration.setAdaptationProbability(adaptionProbabilityTextField.getText());
        vmConfiguration.setRounds(roundsTextField.getText());
        vmConfiguration.setCycles(cyclesTextField.getText());
        vmConfiguration.setEquilibriumRounds((Integer) equilibriumRounds.getValue());
        vmConfiguration.setEquilibriumMaxChange((Integer) equilibriumMaxChange.getValue());
    }

    @Override
    public void show() {
        frame = new JFrame("NewConfigurationView");
        frame.setContentPane(this.ContentPane);
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
    public void addCancelButtonActionlistener(ActionListener listener) {
        cancelButton.addActionListener(listener);

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {
        finishButton.addActionListener(listener);
    }


    @Override
    public VMConfiguration getVMConfiguration() {
        updateVM();
        return this.vmConfiguration;
    }

    @Override
    public void setConfiguration(VMConfiguration configuration) {
        this.vmConfiguration = configuration;

        nameTextField.setText(vmConfiguration.getName());

        gameComboBox.setSelectedItem(vmConfiguration.getGame());
        initComboBox.setSelectedItem(vmConfiguration.getInit());
        pairingComboBox.setSelectedItem(vmConfiguration.getPairingAlg());
        rankingComboBox.setSelectedItem(vmConfiguration.getRankingAlg());
        adaptionComboBox.setSelectedItem(vmConfiguration.getAdaptationAlg());

        adaptionProbabilityTextField.setText(vmConfiguration.getAdaptationProbability());
        roundsTextField.setText(vmConfiguration.getRounds());
        cyclesTextField.setText(vmConfiguration.getCycles());

        equilibriumRounds.setValue(vmConfiguration.getEquilibriumRounds());
        equilibriumMaxChange.setValue(vmConfiguration.getEquilibriumMaxChange());
    }

    @Override
    public void addAdaptionAlgorithm(String name) {
        adaptationAlgs.add(name);
        adaptionComboBox.addItem(name);
    }

    @Override
    public void addPairingAlgorithm(String name) {
        pairingAlgs.add(name);
        pairingComboBox.addItem(name);
    }

    @Override
    public void addRankingAlgorithm(String name) {
        rankingAlgs.add(name);
        rankingComboBox.addItem(name);
    }

    @Override
    public void addInitialization(String name) {
        inits.add(name);
        initComboBox.addItem(name);
    }

    @Override
    public void addGame(String name) {
        games.add(name);
        gameComboBox.addItem(name);
    }

    @Override
    public void addParameters(HashMap<String, String[]> parameters) {
        this.algorithmParameters.putAll(parameters);
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageConfigurationsView) parentView;
    }

    @Override
    public AbstractManageConfigurationsView getParentView() {
        return this.parentView;
    }

    private void setNewParameterTable(Object source) {

        if (source.equals(pairingComboBox)) {
            if (algorithmParameters.get(pairingComboBox.getSelectedItem()) != null) {
                pairingParameterTable.setParameters(algorithmParameters.get(pairingComboBox.getSelectedItem()));
            }

        } else if (source.equals(rankingComboBox)) {
            if (algorithmParameters.get(rankingComboBox.getSelectedItem()) != null) {
                rankingParameterTable.setParameters(algorithmParameters.get(rankingComboBox.getSelectedItem()));
            }

        } else if (source.equals(adaptionComboBox)) {
            if (algorithmParameters.get(adaptionComboBox.getSelectedItem()) != null) {
                adaptionParameterTable.setParameters(algorithmParameters.get(adaptionComboBox.getSelectedItem()));
            }
        }

        update();
    }

    private void createUIComponents() {

        pairingAlgoParamPane = new JScrollPane();
        pairingAlgoParamPane.add(new JLabel("Dies ist ein Test!"));

        gameComboBox = new JComboBox(games.toArray());

        initComboBox = new JComboBox(inits.toArray());

        pairingComboBox = new JComboBox(pairingAlgs.toArray());


        rankingComboBox = new JComboBox(rankingAlgs.toArray());

        adaptionComboBox = new JComboBox(adaptationAlgs.toArray());


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
        ContentPane = new JPanel();
        ContentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(30);
        ContentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(21, 4, new Insets(10, 10, 10, 30), -1, -1));
        scrollPane1.setViewportView(panel1);
        final JLabel label1 = new JLabel();
        label1.setText("Stufenspiel");
        panel1.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Initialisierung");
        panel1.add(label2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Algorithmus zum Bilden von Paaren");
        panel1.add(label3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        panel1.add(pairingComboBox, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Bewertungsalgorithmus");
        panel1.add(label4, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        panel1.add(rankingComboBox, new GridConstraints(9, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Anpassungsalgorithmus");
        panel1.add(label5, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        panel1.add(adaptionComboBox, new GridConstraints(12, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Wahrscheinlichkeit, dass Agent seine Strategie anpasst");
        panel1.add(label6, new GridConstraints(14, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Anzahl der Runden");
        panel1.add(label7, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Maximale Anzahl der Zyklen");
        panel1.add(label8, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        roundsTextField = new JTextField();
        panel1.add(roundsTextField, new GridConstraints(16, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cyclesTextField = new JTextField();
        panel1.add(cyclesTextField, new GridConstraints(17, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panel1.add(separator2, new GridConstraints(15, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1.add(gameComboBox, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(initComboBox, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Name");
        panel1.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(212, 18), null, 0, false));
        nameTextField = new JFormattedTextField();
        nameTextField.setText("");
        panel1.add(nameTextField, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(212, 14), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(212, 14), null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(8, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(212, 14), null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(212, 14), null, 0, false));
        adaptionProbabilityTextField = new JTextField();
        panel1.add(adaptionProbabilityTextField, new GridConstraints(14, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        setPairingParamsButton = new JButton();
        setPairingParamsButton.setText("Parameter setzen");
        panel1.add(setPairingParamsButton, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setRankinParamsButton = new JButton();
        setRankinParamsButton.setText("Parameter setzen");
        panel1.add(setRankinParamsButton, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        setAdaptionParamsButton = new JButton();
        setAdaptionParamsButton.setText("Parameter setzen");
        panel1.add(setAdaptionParamsButton, new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator3 = new JSeparator();
        panel1.add(separator3, new GridConstraints(18, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Runden für Gleichgewichtszustand");
        label10.setToolTipText("Wenn in den letzten w Zyklen maximal x% der Agenten ihre Strategie angepasst haben, ist ein Gleichgewichtszustand erreicht. ");
        panel1.add(label10, new GridConstraints(19, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Maximale Änderungen für Gleichgewichtszustand");
        label11.setToolTipText("Wenn in den letzten w Zyklen maximal x% der Agenten ihre Strategie angepasst haben, ist ein Gleichgewichtszustand erreicht. ");
        panel1.add(label11, new GridConstraints(20, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        equilibriumRounds = new JSpinner();
        panel1.add(equilibriumRounds, new GridConstraints(19, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        equilibriumMaxChange = new JSpinner();
        panel1.add(equilibriumMaxChange, new GridConstraints(20, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("w:");
        panel1.add(label12, new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("x:");
        panel1.add(label13, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        ContentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("fertig");
        panel2.add(finishButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        panel2.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ContentPane;
    }

}

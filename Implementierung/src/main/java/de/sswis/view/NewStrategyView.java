package de.sswis.view;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static de.sswis.util.InputValidator.*;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer gemischten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewStrategyView implements AbstractNewStrategyView {

    private JFrame frame = new JFrame();
    ;

    private final int MAX_LINES = 19;

    private VMStrategy vmStrategy = new VMStrategy();

    private List<String> combinedStrategies = new ArrayList<>();

    private List<JFormattedTextField> probabilityTextFields = new ArrayList<>();
    private List<JComboBox> strategyComboBoxes = new ArrayList<>();

    private JPanel MainPanel;
    private JPanel ContentPane;
    private JPanel panel1 = new JPanel();
    private JTextPane descriptionTextPane;
    private JFormattedTextField nameTextField;
    private JButton addStrategyButton;
    private JButton finishButton;
    private JButton cancelButton;
    private JButton removeLastLineButton;
    private JPanel strategiesPanel;

    private AbstractManageStrategiesView parentView;

    public NewStrategyView() {
        vmStrategy = new VMStrategy();
        combinedStrategies = new ArrayList<String>();
        probabilityTextFields = new ArrayList<JFormattedTextField>();
        strategyComboBoxes = new ArrayList<JComboBox>();
    }

    private void addNewLine() {

        probabilityTextFields.add(new JFormattedTextField());

        JComboBox strategy = new JComboBox(combinedStrategies.toArray());
        strategyComboBoxes.add(strategy);

        int index = strategyComboBoxes.size() - 1;

        strategiesPanel.add(probabilityTextFields.get(index),
                new GridConstraints(1 + index, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        strategiesPanel.add(strategyComboBoxes.get(index),
                new GridConstraints(1 + index, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));


        removeLastLineButton.setEnabled(true);
        addStrategyButton.setEnabled(!(strategyComboBoxes.size() >= MAX_LINES));

        update();

    }

    private void removeLastLine() {
        int index = strategyComboBoxes.size() - 1;

        strategiesPanel.remove(probabilityTextFields.remove(index));
        strategiesPanel.remove(strategyComboBoxes.remove(index));


        removeLastLineButton.setEnabled(!(strategyComboBoxes.isEmpty()));
        addStrategyButton.setEnabled(!(strategyComboBoxes.size() >= MAX_LINES));

        update();

    }

    @Override
    public void update() {
        frame.pack();
    }

    private boolean updateVM() {
        vmStrategy = new VMStrategy();
        String name = nameTextField.getText();
        String desc = descriptionTextPane.getText();
        double percentageSum = 0.0;

        boolean illegalInput = false;

        if (isLegalName(name) && isLegalDescription(desc) && strategyComboBoxes.size() > 0) {
            vmStrategy.setName(name);
            for (int i = 0; i < strategyComboBoxes.size(); i++) {
                String currentStrategy = (String) strategyComboBoxes.get(i).getSelectedItem();
                String currentPercentage = probabilityTextFields.get(i).getText();
                vmStrategy.addStrategy(currentStrategy,
                        currentPercentage);
                if (!isDouble(currentPercentage) || currentStrategy == null) {
                    illegalInput = true;
                    break;
                }
                percentageSum += Double.parseDouble(currentPercentage);
            }
            vmStrategy.setDescription(desc);
        }
        else illegalInput = true;

        if (illegalInput || percentageSum != 1.0) {
            JOptionPane.showMessageDialog(frame, ILLEGAL_INPUT_MSG);
            return false;
        }
        return true;
    }

    @Override
    public void show() {
        frame = new JFrame("Gemischte Strategie Bearbeiten");
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
    public void addCancelButtonActionlistener(ActionListener listener) {
        cancelButton.addActionListener(listener);

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {
        finishButton.addActionListener(listener);
    }

    @Override
    public VMStrategy getVMStrategy() {
        return updateVM() ? this.vmStrategy : null;
    }

    @Override
    public void setStrategy(VMStrategy strategy) {
        this.vmStrategy = strategy;
        this.nameTextField.setText(vmStrategy.getName());
        this.descriptionTextPane.setText(vmStrategy.getDescription());
        for (int i = 0; i < vmStrategy.getCombinedStrategies().size(); i++) {
            addNewLine();

            probabilityTextFields.get(i).setText(vmStrategy.getProbabilities().get(i));
            strategyComboBoxes.get(i).setSelectedItem(vmStrategy.getCombinedStrategies().get(i));
        }
    }

    @Override
    public void addCombinedStrategy(String name) {
        combinedStrategies.add(name);

    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageStrategiesView) parentView;
    }

    @Override
    public AbstractManageStrategiesView getParentView() {
        return this.parentView;
    }


    private void createUIComponents() {
        addStrategyButton = new JButton();
        addStrategyButton.addActionListener(e -> addNewLine());

        removeLastLineButton = new JButton();
        removeLastLineButton.addActionListener(e -> {
            UIManager.put("OptionPane.yesButtonText", "Ja");
            UIManager.put("OptionPane.noButtonText", "Nein");
            int n = JOptionPane.showConfirmDialog(null, "Wollen Sie die Strategie löschen?", "Bestätigung", JOptionPane.YES_NO_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                removeLastLine();
            } else if (n == JOptionPane.NO_OPTION) {
                close();
            }
        });
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
        ContentPane = new JPanel();
        ContentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        MainPanel.add(ContentPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setHorizontalScrollBarPolicy(31);
        ContentPane.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel1.setLayout(new GridLayoutManager(9, 3, new Insets(20, 20, 20, 20), -1, -1));
        scrollPane1.setViewportView(panel1);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Beschreibung:");
        panel1.add(label1, new GridConstraints(7, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        descriptionTextPane = new JTextPane();
        panel1.add(descriptionTextPane, new GridConstraints(8, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Name: ");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JFormattedTextField();
        panel1.add(nameTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addStrategyButton.setText("Kombinierte Strategie hinzufügen");
        panel1.add(addStrategyButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeLastLineButton.setEnabled(false);
        removeLastLineButton.setText("letzte Kombinerte Strategie entfernen");
        panel1.add(removeLastLineButton, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        strategiesPanel = new JPanel();
        strategiesPanel.setLayout(new GridLayoutManager(20, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(strategiesPanel, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$(null, Font.BOLD, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Wahrscheinlichkeit");
        strategiesPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$(null, Font.BOLD, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Kombinierte Strategie");
        strategiesPanel.add(label4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(-1, 50), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        ContentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("fertig");
        panel2.add(finishButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        panel2.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

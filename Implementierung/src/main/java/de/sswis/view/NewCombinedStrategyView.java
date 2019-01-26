package de.sswis.view;


import com.intellij.uiDesigner.core.GridLayoutManager;
import de.sswis.view.model.VMCombinedStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Erstellen oder Bearbeiten einer kombinierten Strategie.
 *
 * @author Sophie Bräuniger
 */
public class NewCombinedStrategyView implements AbstractNewCombinedStrategyView {

    private JFrame frame;

    private VMCombinedStrategy vmCombinedStrategy;

    private List<String> conditions;
    private List<String> baseStrategies;

    private JPanel MainPanel;

    private List<JLabel> priorityLabels;
    private List<JComboBox> conditionComboBoxes;
    private List<JComboBox> strategyComboBoxes;


    private JPanel ContentPane;
    private JButton finishButton;
    private JButton cancelButton;
    private JPanel panel1;
    private JTextPane descriptionTextPane;
    private JFormattedTextField nameTextField;
    private JComboBox comboBox1;
    private JButton addConditionButton;
    private JButton letzteBedingungEntfernenButton;

    private AbstractManageCombinedStrategiesView parentView;


    @Override
    public void update() {

    }

    @Override
    public void show() {
        frame = new JFrame("Kombinierte Strategie Bearbeiten");
        frame.setContentPane(new NewCombinedStrategyView().MainPanel);
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
  
  
    @Override
    public VMCombinedStrategy getCombinedStrategy() {
        return this.vmCombinedStrategy;
    }

    @Override
    public void setCombinedStrategy(VMCombinedStrategy combinedStrategy) {
        this.vmCombinedStrategy = combinedStrategy;
    }

    @Override
    public void addCondition(String name) {

    }

    @Override
    public void addBaseStrategy(String name) {

    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageCombinedStrategiesView) parentView;
    }

    @Override
    public AbstractManageCombinedStrategiesView getParentView() {
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
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

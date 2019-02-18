package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.AbstractManageResultsView;
import de.sswis.view.CustomComponents.ResultTab;
import de.sswis.view.model.VMResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * Ein Fenster zum Verwalten von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public class ManageResultsView implements AbstractManageResultsView {


    private JFrame frame = new JFrame();
    ;

    private List<VMResult> vmResults;

    private List<ResultTab> resultTabs;

    ActionListener deleteListener;

    private JPanel ButtonPanel;
    private JButton saveAndQuitButton;
    private JTabbedPane ResultsPane;
    private JButton deleteButton = new JButton();
    private JPanel MainPanel;

    private AbstractMainView parentView;

    public ManageResultsView() {
        vmResults = new ArrayList<VMResult>();
        resultTabs = new ArrayList<ResultTab>();
    }

    @Override
    public void addResult(VMResult vmResult) {
        vmResults.add(vmResult);
        ResultTab tab = new ResultTab();
        tab.setVmResult(vmResult);
        tab.addDeleteButtonActionlistener(deleteListener);

        resultTabs.add(tab);
        ResultsPane.addTab(vmResult.getName(), tab.$$$getRootComponent$$$());

        update();
    }


    @Override
    public void removeResult(String resultName) {
        for (int i = 0; i < vmResults.size(); i++) {
            if (vmResults.get(i).getName().equals(resultName)) {
                vmResults.remove(i);
                ResultsPane.removeTabAt(i);
                break;
            }
        }

        update();
    }

    @Override
    public void addDeleteResultButtonActionlistener(ActionListener listener) {
        deleteListener = listener;
    }

    @Override
    public void addCloseActionListener(ActionListener listener) {
        saveAndQuitButton.addActionListener(listener);
    }

    @Override
    public VMResult getSelectedVM() {
        return vmResults.get(ResultsPane.getSelectedIndex());
    }

    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
        frame = new JFrame("Ergebnis Verwaltung");
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
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(5, 5, 5, 5), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(ButtonPanel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Schließen");
        ButtonPanel.add(saveAndQuitButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ResultsPane = new JTabbedPane();
        ResultsPane.setTabPlacement(2);
        panel1.add(ResultsPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

}

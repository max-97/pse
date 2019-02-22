package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.util.DataSetHelper;
import de.sswis.view.CustomComponents.ResultTabs.CompareTab;
import de.sswis.view.CustomComponents.ResultTabs.SimpleResultTab;
import de.sswis.view.model.VMAgentHistory;
import de.sswis.view.model.VMResult;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.KeyedValuesDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Ein Fenster zum Vergleichen von Ergebnissen.
 *
 * @author Sophie Bräuniger
 */
public class ShowCompareView implements AbstractShowCompareView {

    private JFrame frame = new JFrame();



    //private List<VMConfiguration> configurations;

    private ArrayList<ArrayList<VMResult>> results = new ArrayList<>();
    private ArrayList<String> resultNames = new ArrayList<>();

    private JPanel MainPanel;
    private JTable simulationTable;
    private DefaultTableModel tableModel;
    private JComboBox repititionComboBox;
    private JButton addButton;
    private JButton removeButton;

    private AbstractMainView parentView;
    private JList configurationList;
    private JButton applyButton;
    private JTabbedPane resultTabbedPane;
    private ArrayList<CompareTab> resultTabList;


    public ShowCompareView() {
        $$$setupUI$$$();
    }

    private void updateconfigSelection() {
        if (configurationList.isSelectionEmpty()) {
            repititionComboBox.setEnabled(false);
            addButton.setEnabled(false);
        } else {
            repititionComboBox.removeAllItems();
            repititionComboBox.addItem("Durchschnitt");
            int max = results.get(configurationList.getSelectedIndex()).size();
            for (int i = 0; i < max; i++) {
                repititionComboBox.addItem(i + ". Wdhl.");
            }
            repititionComboBox.setEnabled(true);
            addButton.setEnabled(true);
        }
    }

    private void updateSimSelection() {
        removeButton.setEnabled(simulationTable.getSelectedRow() != -1);
    }

    private void removeSimulation() {
        int index = simulationTable.getSelectedRow();
        tableModel.removeRow(index);
    }

    private void addSimulation() {
        Object[] data = {configurationList.getModel().getElementAt(configurationList.getSelectedIndex()),
                repititionComboBox.getSelectedItem()};
        tableModel.addRow(data);
    }

    private void createTabs() {
        resultTabList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            CompareTab tab = new CompareTab();
            resultTabList.add(tab);
        }

        resultTabbedPane.addTab("Gleichgewicht", resultTabList.get(0).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Strategien", resultTabList.get(1).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Punkte", resultTabList.get(2).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Ränge", resultTabList.get(3).$$$getRootComponent$$$());


    }

    public void updateChart() {
        KeyedValuesDataset dataset = getAverageEquilibriumChart();
        resultTabList.get(0).setaverageChart(ChartFactory.createPieChart("Gleichgewicht erreicht?", dataset, true, true, false));
        resultTabList.get(0).setDifferenceCharts(getDiffEquilibriumCharts(dataset));
        dataset = getAverageStrategiesChart();
        resultTabList.get(1).setaverageChart(ChartFactory.createPieChart("Strategien", dataset));
        resultTabList.get(1).setDifferenceTable(getDiffStrategiesTable(dataset));

        CategoryDataset categoryDataset = getAveragePointRangeChart();
        resultTabList.get(2).setaverageChart(ChartFactory.createStackedBarChart("Punkteverteilung",
                "Punkte", "Anzahl der Agenten aufgeteilt in Strategien", categoryDataset));
        resultTabList.get(2).setDifferenceTable(getDiffPointRangeCharts(categoryDataset));
        categoryDataset = getAverageRankRangeChart();
        resultTabList.get(3).setaverageChart(ChartFactory.createStackedBarChart("Punkteverteilung",
                "Agentenzahl", "Rangbereich aufgeteilt in Strategien", categoryDataset));
        resultTabList.get(3).setDifferenceTable(getDiffRankRangeCharts(categoryDataset));
    }


    public KeyedValuesDataset getAverageEquilibriumChart() {
        int yes = 0;
        int no = 0;

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));
            String repitition = (String) simulationTable.getModel().getValueAt(i, 1);

            if (repitition.equals("Durchschnitt")) {
                for (int j = 0; j < results.get(index).size(); j++) {
                    if (results.get(index).get(j).reachedEquilibrium()) {
                        yes++;
                    } else {
                        no++;
                    }
                }
            } else {
                int r = Integer.parseInt(repitition.replaceAll("\\. Wdhl\\.", ""));
                if (results.get(index).get(r).reachedEquilibrium()) {
                    yes++;
                } else {
                    no++;
                }
            }
        }

        DefaultKeyedValuesDataset dataset = new DefaultKeyedValuesDataset();
        dataset.setValue("Ja", yes);
        dataset.setValue("Nein", no);

        return dataset;
    }

    public JFreeChart getDiffEquilibriumCharts(KeyedValuesDataset averageDataset) {
        DefaultCategoryDataset diffData = new DefaultCategoryDataset();
        ArrayList<String> rowKeys = new ArrayList<>();
        double[][] data = new double[simulationTable.getRowCount()][2];

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int yes = 0;
            int no = 0;
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));

            String repitition = (String) simulationTable.getModel().getValueAt(i, 1);
            rowKeys.add(resultNames.get(index) + repitition);


            if (repitition.equals("Durchschnitt")) {
                for (int j = 0; j < results.get(index).size(); j++) {
                    if (results.get(index).get(j).reachedEquilibrium()) {
                        yes++;
                    } else {
                        no++;
                    }
                }
            } else {
                int r = Integer.parseInt(repitition.replaceAll("\\. Wdhl\\.", ""));
                if (results.get(index).get(r).reachedEquilibrium()) {
                    yes++;
                } else {
                    no++;
                }
            }
            data[i][0] = (double) yes - (double) averageDataset.getValue("Ja");
            data[i][1] = (double) no - (double) averageDataset.getValue("Nein");
        }
        String[] colKeys = {"Ja", "Nein"};
        diffData = (DefaultCategoryDataset) DatasetUtilities.createCategoryDataset(rowKeys.toArray(new String[0]), colKeys, data);


        JFreeChart barChart = ChartFactory.createBarChart("Gleichgewicht erreicht? Differenz vom Durchschnitt",
                "Eregbnisse", "Differenz vom Durchschnitt", diffData);

        return barChart;
    }

    public KeyedValuesDataset getAverageStrategiesChart() {
        ArrayList<String> strategies = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = getAllAgents();
        int divisor = 1;

        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
        }

        KeyedValuesDataset dataset = DataSetHelper.getKeyedValuesDataSet(strategies, divisor);


        return dataset;
    }

    public JTable getDiffStrategiesTable(KeyedValuesDataset dataset) {

        String[] titles = new String[dataset.getItemCount() + 1];
        titles[0] = "Ergebnis - Wdhl.";
        for (int i = 0; i < dataset.getItemCount(); i++) {
            titles[i + 1] = (String) dataset.getKey(i);
        }
        tableModel = new DefaultTableModel(titles, 0);

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));
            String repitition = (String) simulationTable.getModel().getValueAt(i, 1);
            int divisor = 1;

            if (repitition.equals("Durchschnitt"))
                divisor = results.get(index).size();

            ArrayList<VMAgentHistory> agents = new ArrayList<>();
            agents.addAll(getAgents(index, i));

            ArrayList<String> strategies = new ArrayList<>();
            for (int j = 0; j < agents.size(); j++) {
                strategies.add(agents.get(j).getLastStrategy());
            }

            KeyedValuesDataset diffDataset = DataSetHelper.getKeyedValuesDataSet(strategies, divisor);

            String[] data = new String[titles.length];
            data[0] = (String) simulationTable.getModel().getValueAt(i, 0)
                    + (String) simulationTable.getModel().getValueAt(i, 1);

            for (int k = 0; k < dataset.getItemCount(); k++) {
                String key = (String) dataset.getKey(k);
                if (diffDataset.getKeys().contains(key))
                    data[k + 1] = ((double) diffDataset.getValue(key) - (double) dataset.getValue(key)) + "";
                else
                    data[k + 1] = ((double) diffDataset.getValue(key) - 0.0) + "";
            }

            tableModel.addRow(data);


        }
        JTable table = new JTable(tableModel);

        return table;
    }

    public CategoryDataset getAveragePointRangeChart() {
        ArrayList<String> strategies = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = getAllAgents();
        int divisor = 1;

        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
            points.add(agents.get(i).getLastScore());
        }

        CategoryDataset dataset = DataSetHelper.getCategoryRangeDataset(strategies, points, divisor, 100);

        return dataset;
    }

    public JTable getDiffPointRangeCharts(CategoryDataset dataset) {
        String[] titles = new String[dataset.getColumnCount() + 1];
        titles[0] = "Ergebnis - Wdhl. - Strategie";
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            titles[i + 1] = (String) dataset.getColumnKey(i);
        }
        tableModel = new DefaultTableModel(titles, 0);

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));
            String repitition = (String) simulationTable.getModel().getValueAt(i, 1);
            int divisor = 1;

            if (repitition.equals("Durchschnitt"))
                divisor = results.get(index).size();

            ArrayList<VMAgentHistory> agents = new ArrayList<>();
            agents.addAll(getAgents(index, i));

            ArrayList<String> strategies = new ArrayList<>();
            ArrayList<Integer> points = new ArrayList<>();
            for (int j = 0; j < agents.size(); j++) {
                strategies.add(agents.get(j).getLastStrategy());
                points.add(agents.get(i).getLastScore());
            }

            CategoryDataset diffDataset = DataSetHelper.getCategoryRangeDataset(strategies, points, divisor, 100);

            for (int row = 0; row < dataset.getRowCount(); row++) {
                String rowKey = (String) dataset.getRowKey(row);
                String[] data = new String[titles.length];
                data[0] = (String) simulationTable.getModel().getValueAt(i, 0)
                        + (String) simulationTable.getModel().getValueAt(i, 1)
                        + rowKey;

                for (int col = 0; col < dataset.getColumnCount(); col++) {
                    String colKey = (String) dataset.getColumnKey(col);
                    if (diffDataset.getRowKeys().contains(rowKey) && diffDataset.getColumnKeys().contains(colKey))
                        data[col + 1] = ((double) diffDataset.getValue(rowKey, colKey)
                                - (double) dataset.getValue(rowKey, colKey)) + "";
                    else
                        data[col + 1] = (0.0 - (double) dataset.getValue(rowKey, colKey)) + "";
                }

                tableModel.addRow(data);

            }



        }
        JTable table = new JTable(tableModel);

        return table;
    }

    public CategoryDataset getAverageRankRangeChart() {
        ArrayList<String> strategies = new ArrayList<>();
        ArrayList<Integer> ranks = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = getAllAgents();
        int divisor = 1;

        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
            ranks.add(agents.get(i).getLastRank());
        }

        CategoryDataset dataset = DataSetHelper.getCategoryRangeDataset(strategies, ranks, divisor, 10);

        return dataset;
    }

    public JTable getDiffRankRangeCharts(CategoryDataset dataset) {
        String[] titles = new String[dataset.getColumnCount() + 1];
        titles[0] = "Ergebnis - Wdhl. - Strategie";
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            titles[i + 1] = (String) dataset.getColumnKey(i);
        }
        tableModel = new DefaultTableModel(titles, 0);

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));
            String repitition = (String) simulationTable.getModel().getValueAt(i, 1);
            int divisor = 1;

            if (repitition.equals("Durchschnitt"))
                divisor = results.get(index).size();

            ArrayList<VMAgentHistory> agents = new ArrayList<>();
            agents.addAll(getAgents(index, i));

            ArrayList<String> strategies = new ArrayList<>();
            ArrayList<Integer> ranks = new ArrayList<>();
            for (int j = 0; j < agents.size(); j++) {
                strategies.add(agents.get(j).getLastStrategy());
                ranks.add(agents.get(i).getLastRank());
            }

            CategoryDataset diffDataset = DataSetHelper.getCategoryRangeDataset(strategies, ranks, divisor, 10);

            for (int row = 0; row < dataset.getRowCount(); row++) {
                String rowKey = (String) dataset.getRowKey(row);
                String[] data = new String[titles.length];
                data[0] = (String) simulationTable.getModel().getValueAt(i, 0)
                        + (String) simulationTable.getModel().getValueAt(i, 1)
                        + rowKey;

                for (int col = 0; col < dataset.getColumnCount(); col++) {
                    String colKey = (String) dataset.getColumnKey(col);
                    if (diffDataset.getRowKeys().contains(rowKey) && diffDataset.getColumnKeys().contains(colKey))
                        data[col + 1] = ((double) diffDataset.getValue(rowKey, colKey)
                                - (double) dataset.getValue(rowKey, colKey)) + "";
                    else
                        data[col + 1] = (0.0 - (double) dataset.getValue(rowKey, colKey)) + "";
                }

                tableModel.addRow(data);

            }



        }
        JTable table = new JTable(tableModel);

        return table;
    }


    private ArrayList<VMAgentHistory> getAllAgents() {

        ArrayList<VMAgentHistory> agents = new ArrayList<>();

        for (int i = 0; i < simulationTable.getModel().getRowCount(); i++) {
            int index = resultNames.indexOf(simulationTable.getModel().getValueAt(i, 0));
            agents.addAll(getAgents(index, i));
        }

        return agents;
    }

    private ArrayList<VMAgentHistory> getAgents(int Resultindex, int Simindex) {
        ArrayList<VMAgentHistory> agents = new ArrayList<>();

        String repitition = (String) simulationTable.getModel().getValueAt(Simindex, 1);
        if (repitition.equals("Durchschnitt")) {
            for (int j = 0; j < results.get(Resultindex).size(); j++) {
                agents.addAll(results.get(Resultindex).get(j).getAgentHistories());
            }
        } else {
            int r = Integer.parseInt(repitition.replaceAll("\\. Wdhl\\.", ""));
            agents.addAll(results.get(Resultindex).get(r).getAgentHistories());

        }
        return agents;
    }


    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
        createConfigList();
        createTabs();

        frame = new JFrame("Ergebnisse Vergleichen");
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

    private void createConfigList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < resultNames.size(); i++) {
            model.add(i, resultNames.get(i));
        }

        configurationList.setModel(model);
    }


    private void createUIComponents() {

        String[] titles = {"Konfiguration", "Wiederholung"};
        tableModel = new DefaultTableModel(titles, 0);
        simulationTable = new JTable(tableModel);
        simulationTable.setCellSelectionEnabled(false);
        simulationTable.setColumnSelectionAllowed(false);
        simulationTable.setRowSelectionAllowed(true);

        DefaultListSelectionModel selectionModel = new DefaultListSelectionModel();
        selectionModel.addListSelectionListener(e -> updateSimSelection());
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        simulationTable.setSelectionModel(selectionModel);

        configurationList = new JList();
        configurationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurationList.addListSelectionListener(e -> updateconfigSelection());

        applyButton = new JButton();
        applyButton.addActionListener(e -> updateChart());

        removeButton = new JButton();
        removeButton.addActionListener(e -> removeSimulation());

        addButton = new JButton();
        addButton.addActionListener(e -> addSimulation());


    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public void addVMResult(VMResult vmResult) {
        if (resultNames.contains(vmResult.getName())) {
            results.get(resultNames.indexOf(vmResult.getName())).add(vmResult);
        } else {
            ArrayList<VMResult> list = new ArrayList<>();
            list.add(vmResult);
            resultNames.add(vmResult.getName());
            results.add(list);

        }
        update();
    }

    public void addVMResultList(String resultName, ArrayList<VMResult> results) {
        resultNames.add(resultName);
        this.results.add(results);
        update();
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
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
        final JScrollPane scrollPane1 = new JScrollPane();
        MainPanel.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(20, 20, 20, 20), -1, -1));
        scrollPane1.setViewportView(panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 600), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        resultTabbedPane = new JTabbedPane();
        resultTabbedPane.setTabPlacement(1);
        panel3.add(resultTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 500), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel4.add(separator1, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(602, 2), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Wähle einfache Konfigurationen, die verglichen werden sollen...");
        panel4.add(label1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(602, 18), null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel4.add(scrollPane2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(602, 100), null, 0, false));
        scrollPane2.setViewportView(configurationList);
        repititionComboBox = new JComboBox();
        panel4.add(repititionComboBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton.setEnabled(false);
        addButton.setText("hinzufügen");
        panel4.add(addButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setVerticalScrollBarPolicy(21);
        panel4.add(scrollPane3, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 100), null, 0, false));
        scrollPane3.setViewportView(simulationTable);
        final JLabel label2 = new JLabel();
        label2.setText("zu vergleichende Simulationen");
        panel4.add(label2, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        applyButton.setText("Auswahl anwenden");
        panel4.add(applyButton, new GridConstraints(5, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel4.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(602, 14), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(602, 14), null, 0, false));
        removeButton.setEnabled(false);
        removeButton.setText("entfernen");
        panel4.add(removeButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

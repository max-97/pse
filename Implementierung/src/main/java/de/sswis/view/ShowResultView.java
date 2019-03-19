package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import de.sswis.util.DataSetHelper;
import de.sswis.view.CustomComponents.ResultTabs.SimpleResultTab;
import de.sswis.view.model.VMAgentHistory;
import org.jfree.chart.*;
import de.sswis.view.model.VMConfiguration;
import de.sswis.view.model.VMResult;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ein Fenster zum Anzeigen von Ergebnissen einer Konfiguration.
 *
 * @author Sophie Bräuniger
 */
public class ShowResultView implements AbstractShowResultView {
    private static final int EQUILIBRIUM = 0;
    private static final int STRATEGIES = 1;
    private static final int POINTRANGE = 2;
    private static final int RANKRANGE = 3;
    private static final int POINTHISTORY = 4;
    private static final int STRATEGYHISTORY = 5;

    private List<VMResult> vmResults = new ArrayList<>();

    private List<SimpleResultTab> resultTabList;

    private AbstractMainView parentView;

    private JFrame frame = new JFrame();

    private VMConfiguration vmConfiguration;

    private JButton compareButton;
    private JPanel MainPanel;
    private JLabel configNameLabel;
    private JTabbedPane resultTabbedPane;

    private void createTabs() {
        resultTabList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            SimpleResultTab tab = new SimpleResultTab();
            tab.setMaxReptition(vmResults.size());
            tab.setListener(this, i);
            resultTabList.add(tab);
        }

        resultTabbedPane.addTab("Gleichgewicht", resultTabList.get(EQUILIBRIUM).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Strategien", resultTabList.get(STRATEGIES).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Punkte", resultTabList.get(POINTRANGE).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Ränge", resultTabList.get(RANKRANGE).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Punkteverlauf", resultTabList.get(POINTHISTORY).$$$getRootComponent$$$());
        resultTabbedPane.addTab("Strategienverlauf", resultTabList.get(STRATEGYHISTORY).$$$getRootComponent$$$());


    }

    public void updateChart(SimpleResultTab tab, int tabIndex) {
        switch (tabIndex) {
            case EQUILIBRIUM:
                tab.setChart(getEquilibriumChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            case STRATEGIES:
                tab.setChart(getStrategiesChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            case POINTRANGE:
                tab.setChart(getPointRangeChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            case RANKRANGE:
                tab.setChart(getRankRangeChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            case POINTHISTORY:
                tab.setChart(getPointHistoryChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            case STRATEGYHISTORY:
                tab.setChart(getStrategyHistoryChart(tab.getRepititionNumber(), tab.getFilter(), tab.getFilterParameter()));
                break;
            default:
                break;
        }
    }


    private JFreeChart getEquilibriumChart(int repitition, String filter, String filterParam) {

        int yes = 0;
        int no = 0;

        if (repitition == -1) {
            for (int i = 0; i < vmResults.size(); i++) {
                if (vmResults.get(i).reachedEquilibrium()) {
                    yes++;
                } else {
                    no++;
                }
            }

        } else {
            if (vmResults.get(repitition - 1).reachedEquilibrium()) {
                yes++;
            } else {
                no++;
            }
        }

        DefaultKeyedValuesDataset dataset = new DefaultKeyedValuesDataset();
        dataset.setValue("Ja", yes);
        dataset.setValue("Nein", no);
        JFreeChart pieChart = ChartFactory.createPieChart("Gleichgewicht erreicht?", dataset, true, true, false);


        return pieChart;
    }

    private JFreeChart getStrategiesChart(int repitition, String filter, String filterParam) {
        ArrayList<String> strategies = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = filterAgents(repitition, filter, filterParam);
        int divisor = 1;

        if (repitition == -1)
            divisor = vmResults.size();


        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
        }

        KeyedValuesDataset dataset = DataSetHelper.getKeyedValuesDataSet(strategies, divisor);

        JFreeChart pieChart = ChartFactory.createPieChart("Strategien", dataset);

        return pieChart;
    }

    private JFreeChart getPointRangeChart(int repitition, String filter, String filterParam) {
        ArrayList<String> strategies = new ArrayList<>();
        ArrayList<Integer> points = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = filterAgents(repitition, filter, filterParam);
        int divisor = 1;

        if (repitition == -1)
            divisor = vmResults.size();

        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
            points.add(agents.get(i).getLastScore());
        }

        CategoryDataset dataset = DataSetHelper.getCategoryRangeDataset(strategies, points, divisor, 100);

        JFreeChart chart = ChartFactory.createStackedBarChart("Punkteverteilung",
                "Punkte", "Anzahl der Agenten aufgeteilt in Strategien", dataset);
        return chart;
    }

    private JFreeChart getRankRangeChart(int repitition, String filter, String filterParam) {
        ArrayList<String> strategies = new ArrayList<>();
        ArrayList<Integer> ranks = new ArrayList<>();

        ArrayList<VMAgentHistory> agents = filterAgents(repitition, filter, filterParam);
        int divisor = 1;

        if (repitition == -1)
            divisor = vmResults.size();

        for (int i = 0; i < agents.size(); i++) {
            strategies.add(agents.get(i).getLastStrategy());
            ranks.add(agents.get(i).getLastRank());
        }

        CategoryDataset dataset = DataSetHelper.getCategoryRangeDataset(strategies, ranks, divisor, 10);

        JFreeChart chart = ChartFactory.createStackedBarChart("Punkteverteilung",
                "Agentenzahl", "Rangbereich aufgeteilt in Strategien", dataset);
        return chart;
    }

    private JFreeChart getPointHistoryChart(int repitition, String filter, String filterParam) {
        ArrayList<VMAgentHistory> agents = filterAgents(repitition, filter, filterParam);
        int cycleCount = getMinCycles(agents);

        int[] averageScores = new int[cycleCount];
        for (int i = 0; i < averageScores.length; i++) {
            averageScores[i] = 0;
        }

        for (VMAgentHistory agent : agents) {
            int cycle = 0;
            for (Integer score : agent.getScore()) {
                if (averageScores.length <= cycle) {
                    break;
                }
                averageScores[cycle] += score;
                cycle++;
            }
        }

        for (int i = 0; i < averageScores.length; i++) {
            averageScores[i] = Math.round((float) averageScores[i] / agents.size());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries scoreSeries = new XYSeries("Durschnittliche Punktzahl");

        for (int i = 0; i < cycleCount; i++) {
            scoreSeries.add(i + 1, averageScores[i]);
        }

        dataset.addSeries(scoreSeries);

        return ChartFactory.createXYLineChart("Durschnittspunktzahl per Zyklus", "Zyklus",
                "Punktzahl", dataset);
    }

    private int getMinCycles(ArrayList<VMAgentHistory> agents) {
        int min = Integer.MAX_VALUE;
        for (VMAgentHistory history : agents) {
            int size = history.getRank().size();
            if (size < min) {
                min = size;
            }
        }
        return min;
    }

    private JFreeChart getStrategyHistoryChart(int repitition, String filter, String filterParam) {
        HashMap<String, Integer[]> strategiesCycleCounts = new HashMap<>();
        ArrayList<VMAgentHistory> agents = filterAgents(repitition, filter, filterParam);
        int cycleCount = getMinCycles(agents);

        int divisor = 1;

        if (repitition == -1)
            divisor = vmResults.size();

        for (VMAgentHistory agent : agents) {
            int cycle = 0;
            for (String strategy : agent.getStrategies()) {
                if (cycleCount <= cycle) {
                    break;
                }
                if (strategiesCycleCounts.containsKey(strategy)) {
                    strategiesCycleCounts.get(strategy)[cycle]++;
                } else {
                    Integer[] strategyCycleCounts = new Integer[cycleCount];
                    for (int i = 0; i < strategyCycleCounts.length; i++) strategyCycleCounts[i] = 0;
                    strategiesCycleCounts.put(strategy, strategyCycleCounts);
                    strategyCycleCounts[cycle]++;
                }
                cycle++;
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        for (String strategy : strategiesCycleCounts.keySet()) {
            XYSeries strategySeries = new XYSeries(strategy);
            for (int i = 0; i < cycleCount; i++) {
                strategySeries.add(i + 1, (double) strategiesCycleCounts.get(strategy)[i] / divisor);
            }
            dataset.addSeries(strategySeries);
        }

        return ChartFactory.createXYLineChart("Strategieverteilung per Zyklus", "Zyklus",
                "Anzahl", dataset);
    }

    private ArrayList<VMAgentHistory> filterAgents(int repitition, String filter, String filterParam) {
        //TODO: implement filter
        ArrayList<VMAgentHistory> agents = new ArrayList<>();

        if (repitition == -1) {
            for (int i = 0; i < vmResults.size(); i++) {
                agents.addAll(vmResults.get(i).getAgentHistories());
            }
        } else {
            agents.addAll(vmResults.get(repitition - 1).getAgentHistories());

        }

        return agents;
    }

    @Override
    public void update() {
        frame.pack();
    }

    @Override
    public void show() {
//        createVMs();

        createTabs();

        frame = new JFrame("Ergebnisse");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        update();

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
    public void addCompareButtonActionlistener(ActionListener listener) {
        compareButton.addActionListener(listener);
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }

    @Override
    public void addVMResult(VMResult vmResult) {
        configNameLabel.setText(vmResult.getName());
        vmResults.add(vmResult);
    }

    @Override
    public List<VMResult> getResults() {
        return this.vmResults;
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
        panel1.setLayout(new GridLayoutManager(3, 1, new Insets(20, 20, 20, 20), -1, -1));
        MainPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultTabbedPane = new JTabbedPane();
        resultTabbedPane.setTabPlacement(1);
        panel2.add(resultTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(800, 800), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        configNameLabel = new JLabel();
        Font configNameLabelFont = this.$$$getFont$$$(null, Font.BOLD, -1, configNameLabel.getFont());
        if (configNameLabelFont != null) configNameLabel.setFont(configNameLabelFont);
        configNameLabel.setText("Konfiguration1");
        panel3.add(configNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panel3.add(separator1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        compareButton = new JButton();
        compareButton.setText("Vergleichen mit...");
        panel1.add(compareButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

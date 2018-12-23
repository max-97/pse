package de.sswis.view.model;

import java.util.List;

/**
 *
 */
public class VMGroup {

    private String name;
    private int id;
    private AgentDistribution agents;

    private List<String> strategies;
    private List<AgentDistribution> strategyDistributions;

    private List<String> startCapital;
    private List<AgentDistribution> startCapitalDistributions;

    /**
     * @return
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * @param name
     * @param percentage
     */
    public void addStrategy (String name, int percentage) {}

    /**
     * @param name
     * @param ids
     */
    public void addStrategy (String name, int[] ids) {}

    /**
     * @param capital
     * @param percentage
     */
    public void addStartCapital (int capital, int percentage) {}

    /**
     * @param capital
     * @param ids
     */
    public void addStartCapital (int capital, int[] ids) {}

    /**
     * @return
     */
    public String getToolTipText() {    return ""; }


    /**
     *
     */
    public class AgentDistribution {

        /**
         * @param ids
         */
        public AgentDistribution (int[] ids){}

        /**
         * @param percentage
         */
        public AgentDistribution (int percentage){}

        private boolean ChooseIDs;
        private int[] agentIDs;
        private int percentage;
    }


}

package de.sswis.view.model;

import java.util.List;

/**
 * Gruppen Daten, die alle nötigen Parameter zum Erzeugen einer {@code Group} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
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
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: mehrere variable Parameter, illegale Eingaben.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
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
     * Gibt eine String der wichtige Informationen zu dieser Gruppe zusammenfasst.
     * @return String enthält Kurzbeschreibung der Gruppe
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

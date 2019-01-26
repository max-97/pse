package de.sswis.view.model;

import de.sswis.util.AgentDistribution;

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
    private List<String> agentIntervals;

    private List<String> strategies;
    private List<List<String>> strategyDistributions;

    private List<String> startCapital;
    private List<List<String>> startCapitalDistributions;

    public List<String> getAgentIntervals() {
        return agentIntervals;
    }

    public void setAgentIntervals(List<String> agentIntervals) {
        this.agentIntervals = agentIntervals;
    }

    public List<String> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<String> strategies) {
        this.strategies = strategies;
    }

    public List<List<String>> getStrategyDistributions() {
        return strategyDistributions;
    }

    public void setStrategyDistributions(List<List<String>> strategyDistributions) {
        this.strategyDistributions = strategyDistributions;
    }

    public List<String> getStartCapital() {
        return startCapital;
    }

    public void setStartCapital(List<String> startCapital) {
        this.startCapital = startCapital;
    }

    public List<List<String>> getStartCapitalDistributions() {
        return startCapitalDistributions;
    }

    public void setStartCapitalDistributions(List<List<String>> startCapitalDistributions) {
        this.startCapitalDistributions = startCapitalDistributions;
    }

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
     * Fügt eine neue Strategie, mit ihrer Agentenzuteilung in Prozent, hinzu.
     *
     * @param name Name der Strategie
     * @param percentage Prozentzahl der Agenten mit dieser Strategie
     */
    public void addStrategy (String name, int percentage) {}

    /**
     * Fügt eine neue Strategie, mit ihrer Agentenzuteilung nach ihren IDs, hinzu.
     *
     * @param name Name der Strategie
     * @param ids IDs der Agenten mit dieser Strategie
     */
    public void addStrategy (String name, int[] ids) {}

    /**
     * Fügt eine neue Startkapital, mit seiner Agentenzuteilung in Prozent, hinzu.
     *
     * @param capital Höhe des Startkapitals
     * @param percentage Prozentzahl der Agenten mit diesem Startkapital
     */
    public void addStartCapital (int capital, int percentage) {}

    /**
     * Fügt eine neue Startkapital, mit seiner Agentenzuteilung nach ihren IDs, hinzu.
     *
     * @param capital Höhe des Startkapitals
     * @param ids IDs der Agenten mit diesem Startkapital
     */
    public void addStartCapital (int capital, int[] ids) {}

    /**
     * Gibt eine String der wichtige Informationen zu dieser Gruppe zusammenfasst.
     * @return String enthält Kurzbeschreibung der Gruppe
     */
    public String getToolTipText() {    return ""; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAgents() { return agentIntervals; }

    public void setAgents(List<String> agentIntervals) {
        this.agentIntervals = agentIntervals;
    }
}

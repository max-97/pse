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

    public void addStrategy(String name, List<String> distribution) {
        this.strategies.add(name);
        this.strategyDistributions.add(distribution);
    }

    public void addStartCapital(String capital, List<String> distribution) {
        this.startCapital.add(capital);
        this.startCapitalDistributions.add(distribution);
    }

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

    public List<String> getStrategies() {
        return strategies;
    }

    public List<List<String>> getStrategyDistributions() {
        return strategyDistributions;
    }

    public List<String> getStartCapital() {
        return startCapital;
    }

    public List<List<String>> getStartCapitalDistributions() {
        return startCapitalDistributions;
    }
}

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

    public AgentDistribution getAgents() {
        return agents;
    }

    public void setAgents(AgentDistribution agents) {
        this.agents = agents;
    }
}

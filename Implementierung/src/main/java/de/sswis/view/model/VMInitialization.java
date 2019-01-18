package de.sswis.view.model;

import java.util.List;

/**
 * Initialisierungs Daten, die alle nötigen Parameter zum Erzeugen einer {@code Initialization} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMInitialization {

    private String name;
    private List<VMGroup> groups;
    private int agentCount;

    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: mehrere variable Parameter, illegale Eingaben,
     * Inkonsistenzen von Agentenzuteilungen innerhalb und unterhalb der Gruppen.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Zeigt ob die Initialisierung variable Parameter enthält.
     * @return true wenn es genau einen variablen Parameter gibt, sonst false
     */
    public boolean isMultiInitialisation() { return false; }


    /**
     * Gibt eine String der wichtige Informationen zu dieser Initialisierung zusammenfasst.
     * @return String enthält Kurzbeschreibung der Initialisierung
     */
    public String getToolTipText() {    return ""; }

    public void addGroup(VMGroup group) {
        this.groups.add(group);
    }

    public List<VMGroup> getGroups() {
        return this.groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(int agentCount) {
        this.agentCount = agentCount;
    }
}



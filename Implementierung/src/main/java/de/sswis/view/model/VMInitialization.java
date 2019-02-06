package de.sswis.view.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Initialisierungs Daten, die alle nötigen Parameter zum Erzeugen einer {@code Initialization} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMInitialization {

    private String name;
    private String description;
    private List<VMGroup> groups;
    private int agentCount;
    private int numberOfInstances;
    private boolean relativeDistribution;

    private boolean CapitalToTotalPoints;

    public VMInitialization() {
        name = "";
        description = "";
        groups = new ArrayList<>();
        agentCount = 0;
        relativeDistribution = false;
    }

    /**
     * Zeigt ob die Initialisierung variable Parameter enthält.
     * @return true wenn es genau einen variablen Parameter gibt, sonst false
     */
    public boolean isMultiInitialisation() {
        for (VMGroup g : groups) {
            if (g.hasMultiComponent()) return true;
        }
        return false;
    }


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

    public int getNumberOfInstances() {
        return this.numberOfInstances;
    }

    public boolean hasRelativeDistribution() {
        return this.relativeDistribution;
    }

    public boolean addCapitalToTotalPoints() {
        return CapitalToTotalPoints;
    }

    public void setAddCapitalToTotalPoints(boolean capitalToTotalPoints) {
        CapitalToTotalPoints = capitalToTotalPoints;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public void setRelativeDistribution(boolean distribution) {
        this.relativeDistribution = distribution;
    }

    public String getDescription() {
        return this.description;
    }
}



package de.sswis.view.model;

import de.sswis.model.Agent;

import java.util.Collection;
import java.util.List;

/**
 *Ein Ergebnis zum Speichern von Daten von abgeschlossenen Simulationen.
 *Berechnet Daten zum Anzeigen von Diagrammen vor.
 *
 * @author Sophie Bräuniger
 */
public class VMResult {

    private String name;
    private VMConfiguration vmConfig;
    private Collection<Agent> agents; //TODO: falls möglich, löschen
    private Collection<VMAgentHistory> agentHistories;

    private boolean reachedEquilibrium;


    public boolean reachedEquilibrium() {
        return reachedEquilibrium;
    }

    public void setReachedEquilibrium(boolean reachedEquilibrium) {
        this.reachedEquilibrium = reachedEquilibrium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<VMAgentHistory> getAgentHistories() {
        return agentHistories;
    }

    public void setAgentHistories(Collection<VMAgentHistory> agentHistories) {
        this.agentHistories = agentHistories;
    }

    public VMConfiguration getVmConfig() {
        return vmConfig;
    }

    public void setVmConfig(VMConfiguration vmConfig) {
        this.vmConfig = vmConfig;
    }

    public int getNumberOfRepititions() {
        //TODO: implement me
        return 0;
    }

    public Collection<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Collection<Agent> agents) {
        this.agents = agents;
    }

    public List<VMAgentHistory> filterByGroupID (int[] ids) {return null; }//TODO: implement me

    public List<VMAgentHistory> filterByAgentID (int[] ids) {return null; }//TODO: implement me

    public List<VMAgentHistory> filterByRanking (int[] rankings) {return null; }//TODO: implement me

    public List<VMAgentHistory> filterByTopPercent (int percent) {return null; }//TODO: implement me

    public List<VMAgentHistory> filterByLastPercent (int percent) {return null; }//TODO: implement me


}

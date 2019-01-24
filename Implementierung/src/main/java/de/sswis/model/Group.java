package de.sswis.model;

import java.util.List;

/**
 * Eine Gruppe welche eine Menge von Agenten enthaelt.
 * @author Michel Bodé
 */
public class Group {

    private int id;
    private String name;
    private List<Agent> members;

    /**
     * Erstellt eine Gruppe.
     * @param id ID der Gruppe
     * @param name Name der Gruppe
     */
    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setMembers(List<Agent> members) {
        this.members = members;
    }

    public List<Agent> getMembers { return members; }

    /**
     * Fuegt dieser Gruppe einen neuen Agenten hinzu.
     * @param newMember neuer Agent
     */
    public void addMember(Agent newMember) {
        if (!this.members.contains(newMember) &&  newMember.getGroup().equals(this)) {
            this.members.add(newMember);
        } else if (this.members.contains(newMember)){
            throw new DuplicateObjectNameException("this agent is already a member of this group.");
        } else if (!newMember.getGroup().equals(this)) {
            throw new DuplicateObjectNameException("this agent belong to other group.");
        }
    }
}

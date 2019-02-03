package de.sswis.model;

import java.util.List;
import java.util.ArrayList;

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
        this.members = new ArrayList();
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

    public List<Agent> getMembers() { return members; }

}

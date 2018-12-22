package de.sswis.model;

import java.util.List;

/**
 * Eine Gruppe welche eine Menge von Agenten enthält.
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

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Fügt dieser Gruppe einen neuen Agenten hinzu.
     * @param newMember neuer Agent
     */
    public void addMember(Agent newMember) {

    }
}

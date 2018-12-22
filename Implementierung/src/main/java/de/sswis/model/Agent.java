package de.sswis.model;

/**
 * Ein Agent, welcher entsprechend seiner Strategie Stufenspiele mit anderen Agenten spielen kann.
 * Der Agent besitzt eine {@code History} die seine Zustände aus vergangenen Runden speichert.
 * @author Michel Bodé
 */
public class Agent {

    private int id;
    private int initialScore;
    private History history;
    private Group group;
    private Strategy strategy;

    /**
     * Erstellt einen Agenten.
     * @param id ID des Agenten
     * @param initialScore Startpunktzahl des Agenten
     * @param group Gruppe des Agenten
     * @param initialStrategy Anfangsstrategie des Agenten
     */
    public Agent(int id, int initialScore, Group group, Strategy initialStrategy) {
    }

    public int getId() {
        return id;
    }

    public int getInitialScore() { return initialScore; }


    public History getHistory() { return history; }


    public Group getGroup() {
        return group;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Ersetzt die Strategie des Agenten durch eine neue.
     * @param newStrategy neue Strategie
     */
    public void setStrategy(Strategy newStrategy) {

    }
}

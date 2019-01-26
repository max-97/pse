package de.sswis.model;

/**
 * Ein Agent, welcher entsprechend seiner Strategie Stufenspiele mit anderen Agenten spielen kann.
 * Der Agent besitzt eine {@code History} die seine Zustaende aus vergangenen Runden speichert.
 * @author Michel Bodé
 */
public class Agent {

    private int id;
    private int score;
    private int initialScore;
    private int score;
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
        this.id = id;
        this.initialScore = initialScore;
        this.score = initialScore;
        this.group = group;
        this.strategy = initialStrategy;
        this.history = new History();
        history.setScore(initialScore);
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

    public int getScore() { return score; }

    /**
     * Ersetzt die Strategie des Agenten durch eine neue.
     * @param newStrategy neue Strategie
     */
    public void setStrategy(Strategy newStrategy) {
        if(newStrategy instanceof CombinedStrategy) {
            this.strategy = newStrategy;
        } else {
            MixedStrategy newMixedStrategy = (MixedStrategy)strategy;
            this.strategy = new MixedStrategy(newMixedStrategy.getName(), newMixedStrategy.getCombinedStrategies(),
                    newMixedStrategy.getProbabilities());
        }
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }
}

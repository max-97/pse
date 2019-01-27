package de.sswis.model;


/**
 * Ein Stufenspiel, welches durch seine Payoffs definiert ist.
 * Ein Spiel wird von zwei Agenten gespielt und, abhaengig von deren Aktionen, erhalten diese Payoffs.
 * @author Michel Bodé
 */
public class Game {

    private String name;
    private String description;
    private Tuple[][] payoffs;


    /**
     * Erstellt ein Stufenspiel.
     * @param name Name des Spiels
     * @param description Beschreibung des Spiels
     * @param payoffs Payoffs des Spiels
     */
    public Game(String name, String description, Tuple[][] payoffs) {
        this.name = name;
        this.description = description;
        this.payoffs = payoffs;
    }

    /**
     * Gibt die Payoffs zu bestimmten Aktionen zurueck.
     * @param a1 Aktion des ersten Agenten
     * @param a2 Aktion des zweiten Agenten
     * @return ein Paar von Payoffs
     */
    public Tuple getPayoffs(Action a1, Action a2) {
        int i = 0;
        int j = 0;
        switch (a1) {
            case COOPERATION:
                i = 0;
            case  DEFECTION:
                i = 1;
        }
        switch (a2) {
            case COOPERATION:
                j = 0;
            case DEFECTION:
                i = 1;
        }
        Tuple tuple = payoffs[i][j];
        return tuple;
    }

    /**
     * Das Stufenspiel wird von den zwei Agenten gespielt, entsprechend ihrer Aktionen werden
     * die Payoffs verteilt.
     * @param pair Agentenpaar
     */
    public void playGame(Pair pair) {
        Agent agent1 = pair.getAgent(1);
        Agent agent2 = pair.getAgent(2);
        Action action1 = agent1.getStrategy().calculateAction(agent1, agent2);
        Action action2 = agent2.getStrategy().calculateAction(agent2, agent1);
        Tuple tuple = getPayoffs(action1, action2);
        int score1 = agent1.getScore();
        int pay1 = tuple.getX();
        int score2 = agent2.getScore();
        int pay2 = tuple.getY();
        pair.getAgent(1).getHistory().setCooperatedLastTime(pair.getAgent(2), pair.getAgent(1).getHistory().cooperatedEveryTime(pair.getAgent(2)));
        pair.getAgent(1).getHistory().setCooperatedEveryTime(pair.getAgent(2), action1.equals(Action.COOPERATION));
        pair.getAgent(1).getHistory().setGroupCooperatedLastTime(pair.getAgent(2).getGroup(), pair.getAgent(1).getHistory().groupCooperatedEveryTime(agent2.getGroup()));
        pair.getAgent(1).getHistory().setGroupCooperatedEveryTime(pair.getAgent(2).getGroup(), action1.equals(Action.COOPERATION));
        if (action1.equals(Action.COOPERATION) && air.getAgent(1).getHistory().getAlwaysCooperated()) {
            pair.getAgent(1).getHistory().setAlwaysCooperated(true);
        } else {
            pair.getAgent(1).getHistory().setAlwaysCooperated(false);
        }
        pair.getAgent(1).getHistory().setOpponent(pair.getAgent(2));
        pair.getAgent(2).getHistory().setCooperatedLastTime(pair.getAgent(1), pair.getAgent(2).getHistory().cooperatedEveryTime(pair.getAgent(1)));
        pair.getAgent(2).getHistory().setCooperatedEveryTime(pair.getAgent(1), action2.equals(Action.COOPERATION));
        pair.getAgent(2).getHistory().setGroupCooperatedLastTime(pair.getAgent(1).getGroup(), pair.getAgent(2).getHistory().groupCooperatedEveryTime(pair.getAgent(1).getGroup()));
        pair.getAgent(2).getHistory().setGroupCooperatedEveryTime(pair.getAgent(1).getGroup(), action2.equals(Action.COOPERATION));
        if (action2.equals(Action.COOPERATION) && air.getAgent(2).getHistory().getAlwaysCooperated()) {
            pair.getAgent(2).getHistory().setAlwaysCooperated(true);
        } else {
            pair.getAgent(2).getHistory().setAlwaysCooperated(false);
        }
        [air.getAgent(2).getHistory().setOpponent(pair.getAgent(1));
        pair.getAgent(1).setScore(score1 + pay1);
        pair.getAgent(2).setScore(score2 + pay2);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public class Tuple {
        public int x;
        public int y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}

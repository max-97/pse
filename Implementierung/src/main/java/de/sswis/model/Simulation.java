package de.sswis.model;

import de.sswis.controller.SimulationObserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/**
 * Eine Simulation basierend auf einer Konfiguration, welche gestartet werden kann und nach Beenden
 * Ergebnisse zurueckliefern kann. Die Simulation stoppt wenn es zu einem Gleichgewicht kommt oder
 * die maximale Rundenzahl erreicht wird.
 * @author Michel Bodé
 */
public class Simulation implements Runnable, ObservableSimulation {
    private Configuration config;
    private int repetitions;
    private Agent[] initialAgents;
    private HashMap<Agent, Integer> currentRanking;
    private Pair[] currentPairs;
    private List<SimulationObserver> observers;
    private Result result;
    private final int THRESHOLD = 100;

    /**
     * Erstellt eine Simulation.
     * @param config zugrunde liegende Konfiguration
     */
    public Simulation(Configuration config) {
        this.config = config;
        this.result = new Result();
        this.initialAgents = config.getInit().calculateInitialAgentState();
        this.observers = new LinkedList<>();
    }

    /**
     * Legt die Anzahl an Wiederholungen, dieser {@code Simulation} fest.
     * @param repetitions Anzahl an Wiederholungen
     */
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    /**
     * Startet einen Simulationsdurchlauf.
     */
    private void simulateRun(int repetition) {
        Agent[] agents = copyAgents(initialAgents);
        int maxRounds = config.getRounds();
        int cycleRoundCount = config.getCycleRoundCount();
        Game game = config.getGame();
        int round = 0;
        int cycle = 1;
        boolean equilibriumAchieved = false;
        config.getRankingAlg().setIgnoreInitialScore(config.getInit().getInitialScoreStrategiesOnly());
        currentRanking = config.getRankingAlg().getRankings(agents);

        for(Agent agent : agents) {
            agent.getHistory().setScore(agent.getScore());
            agent.getHistory().setStrategy(agent.getStrategy());
            agent.getHistory().setRank(currentRanking.get(agent));
        }

        while(!equilibriumAchieved && round < maxRounds) {
            currentPairs = config.getPairingAlg().getPairing(agents, game);

            for(Pair pair : currentPairs) {
                game.playGame(pair);
            }

            currentRanking = config.getRankingAlg().getRankings(agents);
            round++;

            for(Agent agent : agents) {
                agent.getHistory().increaseRoundCount();
            }

            if(round == (cycle * cycleRoundCount)) {
                cycle++;
                int adaptationCount = config.getAdaptationAlg().adapt(agents, currentRanking, config.getAdaptationProbability());
                if(adaptationCount < THRESHOLD) {
                    equilibriumAchieved = true;
                }
                for(Agent agent : agents) {
                    agent.getHistory().increaseCycleCount();
                    agent.getHistory().setScore(agent.getScore());
                    agent.getHistory().setStrategy(agent.getStrategy());
                    agent.getHistory().setRank(currentRanking.get(agent));
                }
            }
        }
        result.getAgents().put(repetition, agents);
        result.getEquilibriums().put(repetition, equilibriumAchieved);
    }

    /**
     * Gibt die Agenten nach Beendigung der Simulation zurueck, welche alle Daten bezueglich den Ergebnissen
     * und dem Verlauf der Simulation enthalten.
     * @return Menge an Agenten
     */
    public Result getResults() { return this.result; }

    /**
     * Startet die Simulation neu.
     */
    public void restart() {

    }

    /**
     * Bricht die Simulation ab.
     */
    public void abort() {

    }

    /**
     * Liefert den Namen der Simulation zurück. Der Name ist identisch zum Namen der zugehörigen
     * {@code Configuration}.
     * @return der Name der Simulation
     * @see Configuration#getName()
     */
    public String getName() {
        return this.config.getName();
    }

    private Agent[] copyAgents(Agent[] agents) {
        Agent[] result = new Agent[agents.length];
        for(int i = 0; i < agents.length; i++) {
            Agent newAgent = new Agent(agents[i].getId(), agents[i].getInitialScore(), agents[i].getGroup(),
                    agents[i].getStrategy());
            result[i] = newAgent;
        }
        return result;
    }

    @Override
    public void run() {
        for(int i = 0; i < repetitions; i++) {
            simulateRun(i + 1);
        }
        notifyObservers();
    }

    @Override
    public void addObserver(SimulationObserver o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(SimulationObserver o : observers) {
            o.update(this);
        }
    }

    @Override
    public void deleteObserver(SimulationObserver o) {
        this.observers.remove(o);
    }
}

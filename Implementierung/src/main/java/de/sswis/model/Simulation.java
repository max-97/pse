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
    private boolean stopSimulation;
    private Agent[] initialAgents;
    private List<SimulationObserver> observers;
    private Result result;

    /**
     * Erstellt eine Simulation.
     * @param config zugrunde liegende Konfiguration
     */
    public Simulation(Configuration config) {
        this.repetitions = 1;
        this.config = config;
        this.result = new Result();
        this.initialAgents = config.getInit().calculateInitialAgentState();
        this.observers = new LinkedList<>();
        this.stopSimulation = false;
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
        HashMap<Agent, Integer> currentRanking = config.getRankingAlg().getRankings(agents);
        Pair[] currentPairs;
        int[] adaptationCount = new int[config.getCycles()];

        for(Agent agent : agents) {
            agent.getHistory().setScore(agent.getScore());
            agent.getHistory().setStrategy(agent.getStrategy());
            agent.getHistory().setRank(currentRanking.get(agent));
        }

        while(!stopSimulation && !equilibriumAchieved && round < maxRounds) {
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
                adaptationCount[cycle - 1] = config.getAdaptationAlg().adapt(agents, currentRanking, config.getAdaptationProbability());
                if(isInEquilibrium(adaptationCount, cycle)) {
                    equilibriumAchieved = true;
                }
                cycle++;
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
     * Bricht die Simulation ab.
     */
    public void abort() {
        this.stopSimulation = true;
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

    private boolean isInEquilibrium(int[] adaptationCount, int cycle) {
        if(cycle < config.getEquilibriumRounds()) {
            return false;
        } else {
            int sum = 0;
            for(int i = cycle; i >  cycle - config.getEquilibriumRounds(); i--) sum += adaptationCount[i - 1];
            return (double)sum/config.getEquilibriumRounds() < config.getThreshold() * initialAgents.length;
        }
    }

    @Override
    public void run() {
        for(int i = 0; i < repetitions && !stopSimulation; i++) {
            simulateRun(i + 1);
        }
        if(!stopSimulation) {
            notifyObservers();
        }
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

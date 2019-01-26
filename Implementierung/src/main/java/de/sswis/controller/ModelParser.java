package de.sswis.controller;


import de.sswis.model.*;
import de.sswis.model.algorithms.adaptation.*;
import de.sswis.model.algorithms.pairing.*;
import de.sswis.model.algorithms.ranking.*;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.view.model.*;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Übersetzt Objekte zwischen dem {@code ViewModel} und dem {@code Model}.
 * Dies dient primär dem Umsetzen von Benutzereingaben in eine lauffähige Simulation.
 * Außerdem können Ergebnisse abgelaufener Simulationen für die Ergebnisansicht aufbereitet werden.
 *
 * @author Simon Hügel
 */
public class ModelParser {

    private ModelServiceLoader serviceLoader;
    private FileManager fileManager;
    private ModelProvider provider;

    public ModelParser() {
        this.serviceLoader = new ModelServiceLoader();
        this.fileManager = new FileManager();
        this.provider = ModelProvider.getInstance();
    }

    /**
     * Übersetzt das {@code Simulation}-Objekt einer abgeschlossenen Simulation in ein {@code VMResult}-Objekt.
     * Dabei werden die Daten der Simulation in ein für die Ergebnisansicht konformes Format konvertiert.
     *
     * @param simulation die abgeschlossene Simulation
     * @return die für die Ergebnisansicht aufbereiteten Ergebnisse
     */
    public VMResult parseSimulationToVMResult(Simulation simulation) {
        return null;
    }

    /**
     * Übersetzt ein {@code VMCombinedStrategy}-Objekt in ein {@code CombinedStrategy}-Objekt.
     *
     * @param vmCombinedStrategy die zu übersetzende {@code VMCombinedStrategy}
     * @return die übersetzte {@code CombinedStrategy}
     */
    public CombinedStrategy parseVMCombinedStrategy(VMCombinedStrategy vmCombinedStrategy) {
        String name = vmCombinedStrategy.getName();
        int strategySize = vmCombinedStrategy.getStrategies().size();
        int conditionSize = vmCombinedStrategy.getConditions().size();
        BaseStrategy[] strategies = new BaseStrategy[strategySize];
        Condition[] conditions = new Condition[conditionSize];

        List<String> strategyNames = vmCombinedStrategy.getStrategies();
        for (int i = 0; i < strategySize; i++) {
            for (BaseStrategy b : this.serviceLoader.getBaseStrategyList()) {
                if (b.getName().equals(strategyNames.get(i))) {
                    strategies[i] = b;
                }
            }
        }

        List<String> conditionNames = vmCombinedStrategy.getConditions();
        for (int i = 0; i < conditionSize; i++) {
            for (Condition c : this.serviceLoader.getConditionList()) {
                if (c.getName().equals(conditionNames.get(i))) {
                    c.setParameter(vmCombinedStrategy.getConditionParameter(conditionNames.get(i)));
                    conditions[i] = c;
                }
            }
        }

        return new CombinedStrategy(name, strategies, conditions);
    }

    /**
     * Übersetzt ein {@code VMConfiguration}-Objekt in eine Sammlung an {@code Configuration}-Objekten.
     * Im Falle einer Mehrfachkonfiguration enthält die zurückgegebene Sammlung die {@code n} Einzelkonfigurationen.
     *
     * @param vmConfig die zu übersetzende {@code VMConfiguration}
     * @return die übersetzte {@code Collection<Configuration>}
     */
    public Collection<Configuration> parseVMConfiguration(VMConfiguration vmConfig) {

        String init = vmConfig.getInit();
        VMInitialization vmInitialization;

        try{
            vmInitialization = this.fileManager.loadInitialization(init);
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        ArrayList<Configuration> configurations = new ArrayList<>();

        String name = vmConfig.getName();
        Game game = this.provider.getGame(vmConfig.getName());
        AdaptationAlgorithm adaptationAlgorithm = this.parseAdaptationAlgorithm(vmConfig.getAdaptationAlg(),
                vmConfig.getAdaptationParameters());
        PairingAlgorithm pairingAlgorithm = this.parsePairingAlgorithm(vmConfig.getPairingAlg(),
                vmConfig.getPairingParameters());
        RankingAlgorithm rankingAlgorithm = this.parseRankingAlgorithm(vmConfig.getRankingAlg(),
                vmConfig.getRankingParameters());
        int rounds = Integer.parseInt(vmConfig.getRounds());
        int cycles = Integer.parseInt(vmConfig.getCycles());
        double adaptationProbability = Double.parseDouble(vmConfig.getAdaptationProbability());

        List<Strategy> strategies = new ArrayList<>();
        for (String strategyName : vmConfig.getStrategies()) {
            strategies.add(this.provider.getStrategy(strategyName));
        }

        if (vmInitialization.isMultiInitialisation()) {
            String initName = vmInitialization.getName();

            for (int i = 1; this.provider.getInitialization(initName + i) != null; i++) {

                Initialization initialization = this.provider.getInitialization(initName + i);

                Configuration c = new Configuration(
                        name + i,
                        game,
                        initialization,
                        adaptationAlgorithm,
                        pairingAlgorithm,
                        rankingAlgorithm,
                        rounds,
                        cycles,
                        adaptationProbability,
                        strategies
                );
                configurations.add(c);
            }

        } else {

            Initialization initialization = this.provider.getInitialization(vmInitialization.getName());

            Configuration c = new Configuration(
                    name,
                    game,
                    initialization,
                    adaptationAlgorithm,
                    pairingAlgorithm,
                    rankingAlgorithm,
                    rounds,
                    cycles,
                    adaptationProbability,
                    strategies
            );

            configurations.add(c);
        }
        return configurations;
    }


    /**
     * Übersetzt ein {@code VMGame}-Objekt in ein {@code Game}-Objekt.
     *
     * @param vmGame das zu übersetzende {@code VMGame}
     * @return das übersetzte {@code Game}
     */
    public Game parseVMGame(VMGame vmGame) {

        Game game = new Game(
                vmGame.getName(),
                vmGame.getDescription(),
                parseVMGamePayoffs(vmGame));

        return game;
    }

    private Game.Tuple[][] parseVMGamePayoffs(VMGame vmGame) {

        Game.Tuple[][] outPayoffs = new Game.Tuple[2][2];
        int[][] inPayoffs = vmGame.getPayoffs();

        for (int row = 0; row < 2; row++){
            for (int col = 0; col < 2; col++){
                outPayoffs[row][col] = new Game.Tuple(inPayoffs[row][(2 * col)], inPayoffs[row][(2 * col) + 1]);
            }
        }

        return outPayoffs;
    }

    /**
     * Übersetzt ein {@code VMGroup}-Objekt in ein {@code Group}-Objekt.
     *
     * @param vmGroup die zu übersetzende {@code VMGroup}
     * @return die übersetzte {@code Group}
     */
    public Group parseVMGroupToGroup(VMGroup vmGroup) {
        return null;
    }

    /**
     * Übersetzt ein {@code VMInitialization}-Objekt in eine Sammlung an {@code Initialization}-Objekten.
     * Im Falle einer Mehrfachinitialisierung enthält die zurückgegebene Sammlung die {@code n} Einzelinitialisierungen.
     *
     * @param vmInitialization die zu übersetzende {@code VMInitialization}
     * @return die übersetzte {@code Collection<Initialization>}
     */
    public Collection<Initialization> parseVMInitializationToInitialization(VMInitialization vmInitialization) {

        Collection<Initialization> initializations = new ArrayList<>();

        return initializations; }

    private Initialization parseSingleVMInitializationToInitialization(VMInitialization vmInitialization) {

        Initialization initialization = new Initialization(
                vmInitialization.getName(),
                vmInitialization.getAgentCount());
        /*
        initialization.setGroupDistribution(
                AgentDistribution distribution,
                Group group);
        initialization.setStrategyDistribution(
                AgentDistribution distribution,
                CombinedStrategy strategy,
                Group group);
        initialization.setStrategyDistribution(
                AgentDistribution distribution,
                CombinedStrategy strategy,
                Group group);
        */
        return initialization;
    }

    /**
     * Übersetzt ein {@code VMStrategy}-Objekt in ein {@code Strategy}-Objekt.
     *
     * @param vmStrategy die zu übersetzende {@code VMStrategy}
     * @return die übersetzte {@code Strategy}
     */
    public MixedStrategy parseVMStrategy(VMStrategy vmStrategy) {

        return new MixedStrategy(
                vmStrategy.getName(),
                loadCombinedStrategies(vmStrategy.getCombinedStrategies()),
                probabilitiesArray(vmStrategy.getProbabilities()));
    }

    private double[] probabilitiesArray(List<String> inProbabilities) {

        double[] outProbabilities = new double[inProbabilities.size()];

        for (int i = 0; i < outProbabilities.length; i++) {
            outProbabilities[i] = Double.parseDouble(inProbabilities.get(i));
        }
        return outProbabilities;
    }

    private CombinedStrategy[] loadCombinedStrategies(List<String> inCombinedStrategies) {
        CombinedStrategy[] cs = new CombinedStrategy[inCombinedStrategies.size()];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = this.provider.getCombinedStrategy(inCombinedStrategies.get(i));
        }
        return cs;
    }

    private AdaptationAlgorithm parseAdaptationAlgorithm(String name, HashMap<String, Object> parameters) {
        for (AdaptationAlgorithm a : this.serviceLoader.getAdaptAlgorithmList()) {
            if (a.getName().equals(name)) {
                a.setParameters(parameters);
                return a;
            }
        }
        return null;
    }

    private PairingAlgorithm parsePairingAlgorithm(String name, HashMap<String, Object> parameters) {
        for (PairingAlgorithm p : this.serviceLoader.getPairAlgorithmList()) {
            if (p.getName().equals(name)) {
                p.setParameters(parameters);
                return p;
            }
        }
        return null;
    }

    private RankingAlgorithm parseRankingAlgorithm(String name, HashMap<String, Object> parameters) {
        for (RankingAlgorithm r : this.serviceLoader.getRankAlgorithmList()) {
            if (r.getName().equals(name)) {
                r.setParameters(parameters);
                return r;
            }
        }
        return null;
    }
}

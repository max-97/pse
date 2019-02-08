package de.sswis.controller;


import de.sswis.model.*;
import de.sswis.model.algorithms.adaptation.*;
import de.sswis.model.algorithms.pairing.*;
import de.sswis.model.algorithms.ranking.*;
import de.sswis.model.conditions.Always;
import de.sswis.model.conditions.Condition;
import de.sswis.model.strategies.BaseStrategy;
import de.sswis.util.AgentDistribution;
import de.sswis.view.model.*;
import org.jgrapht.alg.util.Pair;

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
    public Collection<VMResult> parseSimulationToVMResult(Simulation simulation) {
        ArrayList<VMResult> results = new ArrayList<>();

        VMConfiguration configuration;
        try {
            configuration = this.fileManager.loadConfiguration(simulation.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        for (Integer i : simulation.getResults().getAgents().keySet()) {
            VMResult result = new VMResult();

            result.setVmConfig(configuration);
            result.setName(configuration.getName());
            result.setReachedEquilibrium(simulation.getResults().getEquilibriums().get(i));

            Agent[] agents = simulation.getResults().getAgents().get(i);
            Collection<VMAgentHistory> agentHistories = new ArrayList<>();
            for (Agent a : Arrays.asList(agents)) {

                VMAgentHistory vmAH = new VMAgentHistory(a.getId(), a.getGroup().getId(), a.getHistory().getScores(),
                        a.getHistory().getRanks(), a.getHistory().getStrategies());
                agentHistories.add(vmAH);
            }
            result.setAgentHistories(agentHistories);
            results.add(result);
        }
        return results;
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
        BaseStrategy[] strategies = new BaseStrategy[strategySize + 1];
        Condition[] conditions = new Condition[conditionSize + 1];

        List<String> strategyNames = vmCombinedStrategy.getStrategies();
        for (int i = 0; i < strategySize; i++) {
            String strategyName = strategyNames.get(i);
            for (BaseStrategy b : this.serviceLoader.getBaseStrategyList()) {
                if (b.getName().equals(strategyName)) {
                    strategies[i] = b;
                }
            }
        }
        for (BaseStrategy b : this.serviceLoader.getBaseStrategyList()) {
            String defaultStrategy = vmCombinedStrategy.getDefaultStrategy();
            if (b.getName().equals(defaultStrategy)) {
                strategies[strategySize] = b;
            }
        }

        List<String> conditionNames = vmCombinedStrategy.getConditions();
        for (int i = 0; i < conditionSize; i++) {
            for (Condition c : this.serviceLoader.getConditionList()) {
                if (c.getName().equals(conditionNames.get(i))) {
	                HashMap<String, Object> conditionParameters = vmCombinedStrategy.getConditionParameter(i);
	                c.setParameters(conditionParameters);
                    conditions[i] = c;
                }
            }
        }

        conditions[conditionSize] = new Always();

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
        Game game = this.provider.getGame(vmConfig.getGame());
        AdaptationAlgorithm adaptationAlgorithm = this.parseAdaptationAlgorithm(vmConfig.getAdaptationAlg(),
                vmConfig.getAdaptationParameters());
        PairingAlgorithm pairingAlgorithm = this.parsePairingAlgorithm(vmConfig.getPairingAlg(),
                vmConfig.getPairingParameters());
        RankingAlgorithm rankingAlgorithm = this.parseRankingAlgorithm(vmConfig.getRankingAlg(),
                vmConfig.getRankingParameters());
        int rounds = Integer.parseInt(vmConfig.getRounds());
        int cycles = Integer.parseInt(vmConfig.getCycles());
        double adaptationProbability = Double.parseDouble(vmConfig.getAdaptationProbability());

        int equilibriumRounds = vmConfig.getEquilibriumRounds();
        double threshold = vmConfig.getEquilibriumMaxChange() / 100.0;

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
                        equilibriumRounds,
                        threshold
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
                    equilibriumRounds,
                    threshold
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
    public Collection<Initialization> parseVMInitialization(VMInitialization vmInitialization) {
        Collection<Initialization> initializations = new ArrayList<>();

        String name = vmInitialization.getName();
        int agentCount = vmInitialization.getAgentCount();
        List<VMGroup> groups = vmInitialization.getGroups();
        boolean capitalToTotalPoints = vmInitialization.addCapitalToTotalPoints();

        HashMap<Group, VariableDistribution[]> groupDistribution = calculateGroupDistribution(groups);

        HashMap<Pair<Group, Strategy>, VariableDistribution[]> strategyDistributionID =
                calculateStrategyDistribution(groups, groupDistribution.keySet(), false);

        HashMap<Pair<Group, Strategy>, VariableDistribution[]> strategyDistributionRelative =
                calculateStrategyDistribution(groups, groupDistribution.keySet(), true);

        HashMap<Pair<Group, Integer>, VariableDistribution[]> capitalDistributionID =
                calculateCapitalDistribution(groups, groupDistribution.keySet(), false);

        HashMap<Pair<Group, Integer>, VariableDistribution[]> capitalDistributionRelative =
                calculateCapitalDistribution(groups, groupDistribution.keySet(), true);

        if (VariableDistribution.getSize() == 1) {
            // Eine Initialization
            Initialization init = new Initialization(name, agentCount);
            init.setInitialScoreStrategiesOnly(!capitalToTotalPoints);
            if (vmInitialization.hasRelativeDistribution()) {
                for (Group g : groupDistribution.keySet()) {
                    int percent = groupDistribution.get(g)[0].getValue(0);
                    AgentDistribution ad = new AgentDistribution(percent);
                    init.setGroupDistribution(ad, g);
                }
            } else {
                for (Group g : groupDistribution.keySet()) {
                    VariableDistribution[] varDist = groupDistribution.get(g);
                    for (int i = 0; i < varDist.length; i++) {
                        AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                        init.setGroupDistribution(ad, g);
                    }
                }
            }

            for (Pair<Group, Strategy> p : strategyDistributionID.keySet()) {
                VariableDistribution[] varDist = strategyDistributionID.get(p);
                for (int i = 0; i < varDist.length; i++) {
                    AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                    init.setStrategyDistribution(ad, p.getSecond(), p.getFirst());
                }
            }

            for (Pair<Group, Strategy> p : strategyDistributionRelative.keySet()) {
                VariableDistribution[] varDist = strategyDistributionRelative.get(p);
                for (int i = 0; i < varDist.length; i++) {
                    AgentDistribution ad = new AgentDistribution(varDist[i].getValue(0));
                    init.setStrategyDistribution(ad, p.getSecond(), p.getFirst());
                }
            }

            for (Pair<Group, Integer> p : capitalDistributionID.keySet()) {
                VariableDistribution[] varDist = capitalDistributionID.get(p);
                for (int i = 0; i < varDist.length; i++) {
                    AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                    init.setCapitalDistribution(ad, p.getSecond(), p.getFirst());
                }
            }

            for (Pair<Group, Integer> p : capitalDistributionRelative.keySet()) {
                VariableDistribution[] varDist = capitalDistributionRelative.get(p);
                for (int i = 0; i < varDist.length; i++) {
                    AgentDistribution ad = new AgentDistribution(varDist[i].getValue(0));
                    init.setCapitalDistribution(ad, p.getSecond(), p.getFirst());
                }
            }

            initializations.add(init);

        } else {
            // Multi Initialization
            int numberOfInstances = VariableDistribution.getSize();
            for (int j = 0; j < numberOfInstances; j++) {
                Initialization init = new Initialization(name + (j + 1), agentCount);
                init.setInitialScoreStrategiesOnly(!capitalToTotalPoints);

                if (vmInitialization.hasRelativeDistribution()) {
                    for (Group g : groupDistribution.keySet()) {
                        int percent = groupDistribution.get(g)[0].getValue(j);
                        AgentDistribution ad = new AgentDistribution(percent);
                        init.setGroupDistribution(ad, g);
                    }
                } else {
                    for (Group g : groupDistribution.keySet()) {
                        VariableDistribution[] varDist = groupDistribution.get(g);
                        for (int i = 0; i < varDist.length; i++) {
                            AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                            init.setGroupDistribution(ad, g);
                        }
                    }
                }

                for (Pair<Group, Strategy> p : strategyDistributionID.keySet()) {
                    VariableDistribution[] varDist = strategyDistributionID.get(p);
                    for (int i = 0; i < varDist.length; i++) {
                        AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                        init.setStrategyDistribution(ad, p.getSecond(), p.getFirst());
                    }
                }

                for (Pair<Group, Strategy> p : strategyDistributionRelative.keySet()) {
                    VariableDistribution[] varDist = strategyDistributionRelative.get(p);
                    for (int i = 0; i < varDist.length; i++) {
                        AgentDistribution ad = new AgentDistribution(varDist[i].getValue(j));
                        init.setStrategyDistribution(ad, p.getSecond(), p.getFirst());
                    }
                }

                for (Pair<Group, Integer> p : capitalDistributionID.keySet()) {
                    VariableDistribution[] varDist = capitalDistributionID.get(p);
                    for (int i = 0; i < varDist.length; i++) {
                        AgentDistribution ad = new AgentDistribution(varDist[i].getValues());
                        init.setCapitalDistribution(ad, p.getSecond(), p.getFirst());
                    }
                }

                for (Pair<Group, Integer> p : capitalDistributionRelative.keySet()) {
                    VariableDistribution[] varDist = capitalDistributionRelative.get(p);
                    for (int i = 0; i < varDist.length; i++) {
                        AgentDistribution ad = new AgentDistribution(varDist[i].getValue(j));
                        init.setCapitalDistribution(ad, p.getSecond(), p.getFirst());
                    }
                }

                initializations.add(init);
            }
        }

        return initializations;
    }

    private HashMap<Pair<Group, Integer>, VariableDistribution[]> calculateCapitalDistribution(
            List<VMGroup> groups, Set<Group> groupSet, boolean useRelative) {

        HashMap<Pair<Group, Integer>, VariableDistribution[]> distribution = new HashMap<>();

        for (Group g : groupSet) {
            for (VMGroup vmG : groups) {
                if ( (g.getId() == vmG.getId()) && (vmG.getRelativeCapitalDistributions() == useRelative) ) {

                    List<String> startCapital = vmG.getStartCapital();
                    List<List<String>> startCapitalDistributions = vmG.getStartCapitalDistributions();

                    for (int i = 0; i < startCapital.size(); i++) {
                        int capital = Integer.parseInt(startCapital.get(i).trim());
                        VariableDistribution[] varDist = calculateVariables(startCapitalDistributions.get(i));
                        distribution.put(new Pair<Group, Integer>(g, capital), varDist);
                    }
                }
            }
        }

        return distribution;
    }

    private VariableDistribution[] calculateVariables(List<String> values) {
        VariableDistribution[] varDist = new VariableDistribution[values.size()];
        for (int j = 0; j < varDist.length; j++) {
            varDist[j] = new VariableDistribution(values.get(j));
        }
        return varDist;
    }

    private HashMap<Pair<Group, Strategy>, VariableDistribution[]> calculateStrategyDistribution(
            List<VMGroup> groups, Set<Group> groupSet, boolean useRelative) {

        HashMap<Pair<Group, Strategy>, VariableDistribution[]> distribution = new HashMap<>();

        ModelProvider provider = ModelProvider.getInstance();

        for(Group g : groupSet){
            for (VMGroup vmG : groups) {
                if ( (g.getId() == vmG.getId()) && (vmG.getRelativeStrategyDistributions() == useRelative) ) {

                    List<String> strategies = vmG.getStrategies();
                    List<List<String>> strategyDistributions = vmG.getStrategyDistributions();

                    for (int i = 0; i < strategies.size(); i++) {
                        Strategy strategy = provider.getStrategy(strategies.get(i));
                        VariableDistribution[] varDist = calculateVariables(strategyDistributions.get(i));
                        distribution.put(new Pair<Group, Strategy>(g, strategy), varDist);
                    }
                }
            }
        }

        return distribution;
    }

    private HashMap<Group, VariableDistribution[]> calculateGroupDistribution(List<VMGroup> groups) {
        HashMap<Group, VariableDistribution[]> distribution = new HashMap<>();

        for (VMGroup g: groups) {
            Group group = new Group(g.getId(), g.getName());
            VariableDistribution[] varDist = new VariableDistribution[g.getAgents().size()];
            List<String> agents = g.getAgents();
            for (int i = 0; i < varDist.length; i++) {
                varDist[i] = new VariableDistribution(agents.get(i));
            }
            distribution.put(group, varDist);
        }
        return distribution;
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

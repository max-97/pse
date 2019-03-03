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
        double adaptationProbability = 0;
        if(!vmConfig.hasVariableComponent()) {
            adaptationProbability = Double.parseDouble(vmConfig.getAdaptationProbability());
        }

        int equilibriumRounds = vmConfig.getEquilibriumRounds();
        double threshold = vmConfig.getEquilibriumMaxChange() / 100.0;

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

        } else if(vmConfig.hasVariableComponent()){
            Initialization initialization = this.provider.getInitialization(vmInitialization.getName());

            double[] adaptationProbabilities = getDoubleValues(vmConfig.getAdaptationProbability());

            for (int i = 0; i < adaptationProbabilities.length; i++) {

                Configuration c = new Configuration(
                        name + (i + 1),
                        game,
                        initialization,
                        adaptationAlgorithm,
                        pairingAlgorithm,
                        rankingAlgorithm,
                        rounds,
                        cycles,
                        adaptationProbabilities[i],
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

    private double[] getDoubleValues(String input) {
        String[] parts = input.split("-");
        List<Double> values = new ArrayList<>();
        double start = Double.parseDouble(parts[0]);
        double end = Double.parseDouble(parts[1]);
        double step = Double.parseDouble(parts[2]);

        if(start < end) {
            for(int i = 0; start + i * step <= end; i++) {
                values.add(start + i * step);
            }
        } else {
            for(int i = 0; start - i * step >= end; i++) {
                values.add(start - i * step);
            }
        }

        double[] result = new double[values.size()];
        int i = 0;

        for(Double  value: values) {
            result[i] = value;
            i++;
        }
        return result;
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
     * Übersetzt ein {@code VMInitialization}-Objekt in eine Sammlung an {@code Initialization}-Objekten.
     * Im Falle einer Mehrfachinitialisierung enthält die zurückgegebene Sammlung die {@code n} Einzelinitialisierungen.
     *
     * @param vmInitialization die zu übersetzende {@code VMInitialization}
     * @return die übersetzte {@code Collection<Initialization>}
     */
    public Collection<Initialization> parseVMInitialization(VMInitialization vmInitialization){
        Collection<Initialization> initializations = new ArrayList<>();
        String name = vmInitialization.getName();
        int agentCount = vmInitialization.getAgentCount();

        if(!vmInitialization.isMultiInitialisation()) {
            //Simple Initialization
            Initialization init = new Initialization(name, agentCount);
            init.setInitialScoreStrategiesOnly(!vmInitialization.addCapitalToTotalPoints());

            for(VMGroup group : vmInitialization.getGroups()) {
                Group g = new Group(group.getId(), group.getName());

                if(vmInitialization.hasRelativeDistribution()) {
                    AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getAgentsString())*100));
                    init.setGroupDistribution(ad, g);
                } else {
                    AgentDistribution ad = new AgentDistribution(getIDs(group.getAgentsString()));
                    init.setGroupDistribution(ad, g);
                }

                if(group.getRelativeStrategyDistributions()) {
                    for(int i = 0; i < group.getStrategies().size(); i++) {
                        AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStrategyDistributionsStrings().get(i))*100));
                        init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(i)), g);
                    }
                } else {
                    for(int i = 0; i < group.getStrategies().size(); i++) {
                        AgentDistribution ad = new AgentDistribution(getIDs(group.getStrategyDistributionsStrings().get(i)));
                        init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(i)), g);
                    }
                }

                if(group.getRelativeCapitalDistributions()) {
                    for(int i = 0; i < group.getStartCapital().size(); i++) {
                        AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStartCapitalDistributionsStrings().get(i))*100));
                        init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(i)), g);
                    }
                } else {
                    for(int i = 0; i < group.getStartCapital().size(); i++) {
                        AgentDistribution ad = new AgentDistribution(getIDs(group.getStartCapitalDistributionsStrings().get(i)));
                        init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(i)), g);
                    }
                }
            }

            initializations.add(init);
        } else {
            //Multi Initialization
            if(vmInitialization.hasVariableGroupDistribution()) {
                //group distribution is variable
                int size = getDoubleValues(vmInitialization.getGroups().get(0).getAgentsString()).length;

                for(int i = 0; i < size; i++) {
                    Initialization init = new Initialization(name + (i + 1), agentCount);
                    init.setInitialScoreStrategiesOnly(!vmInitialization.addCapitalToTotalPoints());

                    for(VMGroup group : vmInitialization.getGroups()) {
                        Group g = new Group(group.getId(), group.getName());
                        double[] values = getDoubleValues(group.getAgentsString());
                        AgentDistribution groupDistribution = new AgentDistribution((int)Math.round(values[i]*100));
                        init.setGroupDistribution(groupDistribution, g);

                        for (int j = 0; j < group.getStrategies().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStrategyDistributionsStrings().get(j))*100));
                            init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(j)), g);
                        }

                        for (int j = 0; j < group.getStartCapital().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStartCapitalDistributionsStrings().get(j))*100));
                            init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(j)), g);
                        }
                    }
                    initializations.add(init);
                }
            } else if(vmInitialization.hasVariableStrategyDistribution()) {
                //strategy distribution in a group is variable
                VMGroup variableGroup = null;

                for(VMGroup group : vmInitialization.getGroups()) {
                    if(group.hasVariableStrategyDistribution()) variableGroup = group;
                }

                List<String> strategyDistribution = variableGroup.getStrategyDistributionsStrings();
                double[][] values = new double[strategyDistribution.size()][getDoubleValues(strategyDistribution.get(0)).length];

                for(int i = 0; i < strategyDistribution.size(); i++ ) {
                    values[i] = getDoubleValues(variableGroup.getStrategyDistributionsStrings().get(i));
                }

                for(int i = 0; i < values[0].length; i++) {
                    Initialization init = new Initialization(name + (i + 1), agentCount);
                    init.setInitialScoreStrategiesOnly(!vmInitialization.addCapitalToTotalPoints());

                    for(VMGroup group : vmInitialization.getGroups()) {
                        Group g = new Group(group.getId(), group.getName());
                        AgentDistribution groupDistribution = new AgentDistribution((int)Math.round(Double.parseDouble(group.getAgentsString())*100));
                        init.setGroupDistribution(groupDistribution, g);

                        if(group.getId() == variableGroup.getId()) {
                            for (int j = 0; j < group.getStrategies().size(); j++) {
                                AgentDistribution ad = new AgentDistribution((int)Math.round(values[j][i]*100));
                                init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(j)), g);
                            }
                        } else {
                            for (int j = 0; j < group.getStrategies().size(); j++) {
                                AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStrategyDistributionsStrings().get(j))*100));
                                init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(j)), g);
                            }
                        }
                        for (int j = 0; j < group.getStartCapital().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStartCapitalDistributionsStrings().get(j))*100));
                            init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(j)), g);
                        }
                    }
                    initializations.add(init);
                }

            } else if(vmInitialization.hasVariableCapitalDistribution()){
                //capital distribution in a group is variable
                VMGroup variableGroup = null;

                for(VMGroup group : vmInitialization.getGroups()) {
                    if(group.hasVariableCapitalDistribution()) variableGroup = group;
                }

                List<String> capitalDistribution = variableGroup.getStartCapitalDistributionsStrings();
                double[][] values = new double[capitalDistribution.size()][getDoubleValues(capitalDistribution.get(0)).length];

                for(int i = 0; i < capitalDistribution.size(); i++ ) {
                    values[i] = getDoubleValues(variableGroup.getStartCapitalDistributionsStrings().get(i));
                }

                for(int i = 0; i < values[0].length; i++) {
                    Initialization init = new Initialization(name + (i + 1), agentCount);
                    init.setInitialScoreStrategiesOnly(!vmInitialization.addCapitalToTotalPoints());

                    for(VMGroup group : vmInitialization.getGroups()) {
                        Group g = new Group(group.getId(), group.getName());
                        AgentDistribution groupDistribution = new AgentDistribution((int)Math.round(Double.parseDouble(group.getAgentsString())*100));
                        init.setGroupDistribution(groupDistribution, g);

                        if(group.getId() == variableGroup.getId()) {
                            for (int j = 0; j < group.getStartCapital().size(); j++) {
                                AgentDistribution ad = new AgentDistribution((int)Math.round(values[j][i]*100));
                                init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(j)), g);
                            }
                        } else {
                            for (int j = 0; j < group.getStartCapital().size(); j++) {
                                AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStartCapitalDistributionsStrings().get(j))*100));
                                init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(j)), g);
                            }
                        }
                        for (int j = 0; j < group.getStrategies().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStrategyDistributionsStrings().get(j))*100));
                            init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(j)), g);
                        }
                    }
                    initializations.add(init);
                }
            } else {
                //capital in a group is variable
                VMGroup variableGroup = null;
                int variableCapitalIndex = 0;

                for(VMGroup group : vmInitialization.getGroups()) {
                    if(group.hasVariableCapital()) variableGroup = group;
                }

                for(String s : variableGroup.getStartCapital()) {
                    if(s.matches("\\d+ - \\d+ - \\d+")) variableCapitalIndex = variableGroup.getStartCapital().indexOf(s);
                }

                int[] values = getIntValues(variableGroup.getStartCapital().get(variableCapitalIndex));

                for(int i = 0; i < values.length; i++) {
                    Initialization init = new Initialization(name + (i + 1), agentCount);
                    init.setInitialScoreStrategiesOnly(!vmInitialization.addCapitalToTotalPoints());

                    for(VMGroup group : vmInitialization.getGroups()) {
                        Group g = new Group(group.getId(), group.getName());
                        AgentDistribution groupDistribution = new AgentDistribution((int)Math.round(Double.parseDouble(group.getAgentsString())*100));
                        init.setGroupDistribution(groupDistribution, g);

                        for (int j = 0; j < group.getStartCapital().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStartCapitalDistributionsStrings().get(j))*100));
                            if(group.getId() == variableGroup.getId() && variableCapitalIndex == j) {
                                init.setCapitalDistribution(ad, values[i], g);
                            } else {
                                init.setCapitalDistribution(ad, Integer.parseInt(group.getStartCapital().get(j)), g);
                            }
                        }

                        for (int j = 0; j < group.getStrategies().size(); j++) {
                            AgentDistribution ad = new AgentDistribution((int)Math.round(Double.parseDouble(group.getStrategyDistributionsStrings().get(j))*100));
                            init.setStrategyDistribution(ad, provider.getStrategy(group.getStrategies().get(j)), g);
                        }
                    }
                    initializations.add(init);
                }
            }
        }
        return initializations;
    }

    private int[] getIntValues(String input) {
        String[] parts = input.split("-");
        List<Integer> values = new ArrayList<>();
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);
        int step = Integer.parseInt(parts[2]);

        if(start < end) {
            for(int i = start; i <= end; i += step) {
                values.add(i);
            }
        } else {
            for(int i = start; i >= end; i-= step) {
                values.add(i);
            }
        }

        int[] result = new int[values.size()];
        int i = 0;

        for(Integer id : values) {
            result[i] = id;
            i++;
        }

        return result;
    }

    private int[] getIDs(String input) {
        String[] parts = input.split(",");
        List<Integer> ids = new ArrayList<>();

        for(String s : parts) {
            if(s.matches("\\d+ - \\d+")) {
                String[] interval = s.split("-");
                for(int i = Integer.parseInt(interval[0]); i <= Integer.parseInt(interval[1]); i++) ids.add(i);
            } else {
                ids.add(Integer.parseInt(s));
            }
        }

        Collections.sort(ids);
        int[] result = new int[ids.size()];
        int i = 0;

        for(Integer id : ids) {
            result[i] = id;
            i++;
        }

        return result;
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

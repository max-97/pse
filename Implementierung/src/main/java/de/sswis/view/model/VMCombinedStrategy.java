package de.sswis.view.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Kombinierte Strategie Daten, die alle nötigen Parameter zum Erzeugen einer {@code CombinedStrategy} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMCombinedStrategy {
    private String name;
    private String description;

    private String defaultStrategy;
    private List<String> baseStrategies = new ArrayList<>(); //    baseStrategies.length()
    private List<String> conditions = new ArrayList<>();     // == conditions.length()

    public List<String> getStrategies() {
        return this.baseStrategies;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    /**
     * Fügt ein Paar von Bedingung und Basisstrategie hinzu.
     * Eventuell von der Bedingung benötigte Parameter werden dem String angehängt.
     * <br>
     * Bsp. "Delta20,5" entspricht der Bedingung Delta mit Delta.DELTA = 20.5
     *
     * @param baseStrategy Name der Basisstrategie
     * @param condition Bedingung
     */
    public void addStrategy(String baseStrategy, String condition) {
        baseStrategies.add(baseStrategy);
        conditions.add(condition);
    }

    public void addStrategyList(List<String> baseStrategies, List<String> conditions) {
        //baseStrategies.size() == conditions.size()
        Iterator<String> it1 = baseStrategies.iterator();
        Iterator<String> it2 = conditions.iterator();

        while (it1.hasNext()) { // == it2.hasNext()
            addStrategy(it1.next(), it2.next());
        }
    }

    public void setStrategyList(List<String> baseStrategies, List<String> conditions) {
        //baseStrategies.size() == conditions.size()
        this.baseStrategies = new ArrayList<>(baseStrategies.size());
        this.conditions = new ArrayList<>(baseStrategies.size());
        addStrategyList(baseStrategies, conditions);
    }

    /**
     * Setzt die Standard-Strategie, diejenige mit niedrigster Priorität und ohne Bedingung.
     * @param baseStrategy Name der Basisstrategie
     */
    public void setDefaultStrategy(String baseStrategy) {
        this.defaultStrategy = baseStrategy;
    }

    public String getDefaultStrategy() {
        return defaultStrategy;
    }

    /**
     * Gibt eine String der wichtige Informationen zu dieser kombinierten Strategie zusammenfasst.
     * @return String enthält Kurzbeschreibung der kombinierten Strategie
     */
    public String getToolTipText() {    return ""; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Object> getConditionParameter(String conditionName) {
        return null;
    }

}

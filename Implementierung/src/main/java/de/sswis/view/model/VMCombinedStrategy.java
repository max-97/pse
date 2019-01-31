package de.sswis.view.model;

import java.util.ArrayList;
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

    public List<String> getStrategies() {
        return this.baseStrategies;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    public double getConditionParameter(String conditionName) {
        return 0;
    }

}

package de.sswis.view.model;

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

    private List<String> conditions;
    private List<String> strategies;


    /**
     * @return
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * @param strategyName
     * @param condition
     */
    public void addStrategy(String strategyName, String condition) {}

    /**
     * @param strategyName
     * @param condition
     * @param x
     */
    public void addStrategy(String strategyName, String condition, double x) {}

    /**
     * @param strategyName
     * @param condition
     * @param x
     */
    public void addStrategy(String strategyName, String condition, int x) {}

    /**
     * @param strategyName
     */
    public void setDefaultStrategy(String strategyName) {}


    /**
     * @return
     */
    public String getToolTipText() {    return ""; }



}

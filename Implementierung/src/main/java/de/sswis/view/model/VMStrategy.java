package de.sswis.view.model;

import java.util.List;

/**
 * Gemischte Strategie Daten, die alle nötigen Parameter zum Erzeugen einer {@code Strategy} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMStrategy {

    private String name;
    private String description;


    private List<String> combinedStrategies;
    private List<String> probabilities;

    /**
     * @return
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * @return
     */
    public String getToolTipText() {    return ""; }

}

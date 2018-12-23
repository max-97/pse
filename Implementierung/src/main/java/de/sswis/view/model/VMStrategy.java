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
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: illegale Eingaben, Inkonsistenzen innerhalb der Wahrscheinlichkeiten.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Gibt eine String der wichtige Informationen zu dieser gemischten Strategie zusammenfasst.
     * @return String enthält Kurzbeschreibung der gemischten Strategie
     */
    public String getToolTipText() {    return ""; }

}

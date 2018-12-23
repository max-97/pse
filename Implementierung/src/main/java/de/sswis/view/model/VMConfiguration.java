package de.sswis.view.model;

import java.util.List;

/**
 * Konfigurations Daten, die alle nötigen Parameter zum Erzeugen einer {@code Configuration} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMConfiguration {


    private String name;

    private String game;
    private String init;
    private String adaptationAlg;
    private String pairingAlg;
    private String rankingAlg;
    private String rounds;
    private String cycles;
    private String adaptationProbability;
    private List<String> strategies;

    private VMResult result;

    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: mehrere variable Parameter, illegale Eingaben.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Zeigt ob die Konfiguration variable Parameter enthält.
     * @return true wenn es genau einen variablen Parameter gibt, sonst false
     */
    public boolean isMultiConfiguration() { return false; }

    /**
     *
     *
     *
     * @return
     */
    public boolean hasResult () {
        //TODO: implement me
        return false;
    }

    /**
     * Gibt eine String der wichtige Informationen zu dieser Konfiguration zusammenfasst.
     * @return String enthält Kurzbeschreibung der Konfiguration
     */
    public String getToolTipText() {    return ""; }

}

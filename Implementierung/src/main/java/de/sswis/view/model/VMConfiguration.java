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
     * @return
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * @return
     */
    public boolean isMultiConfiguration() { return false; }

    /**
     * @return
     */
    public boolean hasResult () {
        //TODO: implement me
        return false;
    }

    /**
     * @return
     */
    public String getToolTipText() {    return ""; }

}

package de.sswis.view.model;

/**
 * Stufenspiel Daten, die alle nötigen Parameter zum Erzeugen eines {@code Game} enthält.
 * Erhält Nutzereingaben von der Benutzeroberfäche und prüft diese auf Konsistenz und Korrektheit.
 *
 * @author Sophie Bräuniger
 */
public class VMGame {

    private String name;
    private String description;
    private int[][] payoffs;

    /**
     * Zeigt ob die gespeicherten Daten konsistent und korrekt sind.
     * Fehlerhafte Daten beinhalten: illegale Eingaben.
     *
     * @return true wenn die Daten korrekt sind und false wenn sie fehlerhaft sind.
     */
    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    /**
     * Gibt eine String der wichtige Informationen des Stufenspiels zusammenfasst.
     * @return String enthält Kurzbeschreibung des Stufenspiels
     */
    public String getToolTipText() {    return ""; }


}

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

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
     * Gibt eine String der wichtige Informationen des Stufenspiels zusammenfasst.
     * @return String enthält Kurzbeschreibung des Stufenspiels
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

    public int[][] getPayoffs() {
        return payoffs;
    }

    public void setPayoffs(int[][] payoffs) {
        this.payoffs = payoffs;
    }

    public void setPayoffEntry(int x, int y, int value) {
        this.payoffs[x][y] = value;
    }

}

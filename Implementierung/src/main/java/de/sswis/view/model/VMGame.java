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

    /**
     * setzt die Parameter der Payoffs, wie in der Tabelle:
     * Spieler1/Spieler2    K                       D
     *      K               int[0][0]/int[0][1]     int[0][2]/int[0][3]
     *      D               int[1][0]/int[1][1]     int[1][2]/int[1][3]
     * @param payoffs
     */
    public void setPayoffs(int[][] payoffs) {
        this.payoffs = payoffs;
    }

    public void setPayoffEntry(int x, int y, int value) {
        this.payoffs[x][y] = value;
    }

}

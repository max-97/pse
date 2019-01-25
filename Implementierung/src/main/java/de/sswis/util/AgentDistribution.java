package de.sswis.util;

/**
 * Beschreibt die Zuordnung von Agenten. Wird bei der Erstellung von Initialisierungen verwendet
 * um die Verteilung der Agenten auf Gruppen, initiale kombinierte Strategien und Startkapital
 * zu beschreiben.
 */
public class AgentDistribution {

    private boolean usesIDs;
    private int[] agentIDs;
    private int percentage;

    /**
     * Erzeugt eine Zuordnung basierend auf den IDs der Agenten. Die entsprechenden Agenten werden der Gruppe
     * zugeordnet bzw. erhalten die entsprechende kombinierte Strategie oder Startkapital.
     *
     * @param ids die IDs der Agenten
     */
    public AgentDistribution (int[] ids) {
        this.agentIDs = ids;
        this.usesIDs = true;
    }

    /**
     * Erzeugt eine Zuordnung basierend auf dem prozentualen Anteil. Die Gruppenzuteilung erfolgt im Bezug auf
     * die Gesamtanzahl der Agenten. Die Zuordnung der kombinierten Strategien erfolgt im Bezug auf die Anzahl der
     * Agenten in einer Gruppe (siehe Beispiel). Bei der Zuordnung wird zuerst die Zuordnung durch IDs bestimmt, dann
     * durch den prozentual Anteil. Welche Agenten ausgewählt werden ist zufälligg es werden keine Agenten ausgewählt
     * die bereits zugeordnet wurden.
     * <br>
     * Beispiel:
     * <br>
     * 50% der Agenten sind in Gruppe A, es gibt 100 Agenten insgesamt. Es werden 50 Agenten zu Gruppe A zugeordnet.
     * <br>
     * 10% der Agenten in Gruppe A haben die kombinierte Strategie S1, Gruppe A hat 50 Agenten. Es bekommen 5 Agenten
     * die kombinierte Strategie S1
     *
     * @param percentage der prozentuale Anteil
     */
    public AgentDistribution (int percentage) {
        this.percentage = percentage;
        this.usesIDs = false;
    }

    public boolean usesIDS() {
        return this.usesIDs;
    }

    public int[] getAgentIDs() {
        return this.agentIDs;
    }

    public int getPercentage() {
        return this.percentage;
    }
}

package de.sswis.controller;

import de.sswis.model.Simulation;

/**
 * Observer zum Beobachten von {@link Simulation Simulationen}. Sobald eine {@code Simulation} terminiert hat,
 * benachrichtigt sie alle ihre {@code SimulationObserver}.
 * <br>
 * Hinweis: Wir verwenden hier nicht das {@code Observer} Interface von Java, da es als {@code Deprecated} markiert
 * wurde.
 *
 * @author Max Braun
 */
public interface SimulationObserver {

    /**
     * Wird von der beobachteten {@code Simulation} aufgerufen, sobald sie einen Gleichgewichtszustand oder die maximale
     * Zyklenanzahl erreicht hat.
     *
     * @param sim die beendete {@code Simulation}
     */
    void update(Simulation sim);
}

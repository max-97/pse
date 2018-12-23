package de.sswis.view;

import de.sswis.view.model.VMGame;

/**
 *Ein Fenster zum Verwalten von Stufenspielen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageGamesView {

    /**
     * Fügt ein Stufenspiel hinzu.
     * @param game die Benutzereingaben zum Stufenspiel
     */
    void addGame(VMGame game);

    /**
     * Löscht ein Stufenspiel.
     * @param gameName der Name des Stufenspiel
     */
    void removeGame(String gameName);

    /**
     *Aktualisiert alle Komponenten.
     */
    void update();

}

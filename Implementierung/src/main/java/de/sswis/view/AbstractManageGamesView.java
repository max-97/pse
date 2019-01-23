package de.sswis.view;

import de.sswis.view.model.VMGame;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Verwalten von Stufenspielen.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractManageGamesView extends AbstractView {

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
     * Fügt ein ActionListener zum Button neues Stufenspiel hinzu.
     * @param listener ActionListener
     */
    void addNewGameButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Stufenspiel bearbeiten hinzu.
     * @param listener ActionListener
     */
    void addEditGameButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Stufenspiel löschen hinzu.
     * @param listener ActionListener
     */
    void addDeleteGameButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Abbrechen hinzu.
     * @param listener ActionListener
     */
    void addCancelButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Änderungen speichern und schließen hinzu.
     * @param listener ActionListener
     */
    void addSaveQuitButtonActionlistener(ActionListener listener);

    VMGame getSelectedVM();

    AbstractMainView getParentView();
}

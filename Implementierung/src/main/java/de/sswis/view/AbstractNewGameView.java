package de.sswis.view;

import de.sswis.view.model.VMGame;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten eines Stufenspiels.
 *
 * @author Sophie Bräuniger
 */
public interface AbstractNewGameView extends AbstractView {

    /**
     * Fügt ein ActionListener zum Button Abbrechen hinzu.
     * @param listener ActionListener
     */
    void addCancelButtonActionlistener(ActionListener listener);

    /**
     * Fügt ein ActionListener zum Button Fertig hinzu.
     * @param listener ActionListener
     */
    void addFinishButtonActionlistener(ActionListener listener);


    VMGame getVMGame();

    AbstractManageGamesView getParentView();

    void setGame(VMGame game);
}

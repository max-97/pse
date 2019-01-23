package de.sswis.view;

import de.sswis.view.AbstractNewGameView;
import de.sswis.view.model.VMGame;

import java.awt.event.ActionListener;

/**
 *Ein Fenster zum Erstellen oder Bearbeiten eines Stufenspiels.
 *
 * @author Sophie Bräuniger
 */
public class NewGameView implements AbstractNewGameView {

    private VMGame vmGame;
    private AbstractManageGamesView parentView;


    @Override
    public void update() {

    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

    @Override
    public void setParentView(AbstractView parentView) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addFinishButtonActionlistener(ActionListener listener) {

    }

    @Override
    public VMGame getVMGame() {
        return null;
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractManageGamesView) parentView;
    }

    @Override
    public AbstractManageGamesView getParentView() {
        return this.parentView;
    }
}

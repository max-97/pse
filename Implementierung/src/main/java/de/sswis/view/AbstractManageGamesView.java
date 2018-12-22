package de.sswis.view;

import de.sswis.view.model.VMGame;

public interface AbstractManageGamesView {

    void addGame(VMGame game);
    void removeGame(String gameName);

    void update();

}

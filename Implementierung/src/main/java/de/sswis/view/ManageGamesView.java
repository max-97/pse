package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.view.model.VMGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von Stufenspielen.
 *
 * @author Sophie Bräuniger
 */
public class ManageGamesView implements AbstractManageGamesView {

    private List<VMGame> vmGames;


    private JTextPane textPane1;
    private JButton deleteButton;
    private JButton newGameButton;
    private JButton cancelButton;
    private JButton saveAndQuitButton;
    private JButton editButton;
    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JTabbedPane GamesPane;
    private JLabel upLeftPayOffLabel;
    private JLabel bottomLeftPayOffLabel;
    private JLabel upRightPayOffLabel;
    private JLabel bottomRightPayOffLabel;
    private JLabel nameLabel;
    private JLabel descriptionLabel;

    @Override
    public void addGame(VMGame game) {

    }

    @Override
    public void removeGame(String gameName) {

    }

    @Override
    public void addNewGameButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addEditGameButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addDeleteGameButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {

    }

    @Override
    public void update() {

    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}

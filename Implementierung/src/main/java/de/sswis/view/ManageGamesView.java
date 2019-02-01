package de.sswis.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.sswis.model.Game;
import de.sswis.view.CustomComponents.GameTab;
import de.sswis.view.model.VMGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein Fenster zum Verwalten von Stufenspielen.
 *
 * @author Sophie Bräuniger
 */
public class ManageGamesView implements AbstractManageGamesView {

    private JFrame frame;

    private List<VMGame> vmGames;

    private List<GameTab> gameTabs;

    ActionListener editListener;
    ActionListener deleteListener;

    private JButton newGameButton;
    private JButton cancelButton;
    private JButton saveAndQuitButton;
    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JTabbedPane GamesPane;

    private AbstractMainView parentView;

    public ManageGamesView() {
        vmGames = new ArrayList<VMGame>();
        gameTabs = new ArrayList<GameTab>();
    }


    @Override
    public void addGame(VMGame game) {
        vmGames.add(game);
        GameTab tab = new GameTab(game);
        tab.addDeleteButtonActionlistener(deleteListener);
        tab.addEditButtonActionlistener(editListener);

        gameTabs.add(tab);
        GamesPane.addTab(game.getName(), tab.$$$getRootComponent$$$());
    }


    @Override
    public void removeGame(String gameName) {
        for (int i = 0; i < vmGames.size(); i++) {
            if (vmGames.get(i).getName().equals(gameName)) {
                vmGames.remove(i);
                gameTabs.remove(i);
                GamesPane.removeTabAt(i);
                break;
            }
        }
    }

    @Override
    public void addNewGameButtonActionlistener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    @Override
    public void addEditGameButtonActionlistener(ActionListener listener) {
        editListener = listener;
    }

    @Override
    public void addDeleteGameButtonActionlistener(ActionListener listener) {
        deleteListener = listener;
    }

    @Override
    public void addCancelButtonActionlistener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    @Override
    public void addSaveQuitButtonActionlistener(ActionListener listener) {
        saveAndQuitButton.addActionListener(listener);
    }

    @Override
    public VMGame getSelectedVM() {
        return vmGames.get(GamesPane.getSelectedIndex());
    }

    @Override
    public void update() {
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void show() {
        frame = new JFrame("Spiele Verwaltung");
        frame.setContentPane(this.MainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public void setParentView(AbstractView parentView) {
        this.parentView = (AbstractMainView) parentView;
    }

    @Override
    public AbstractMainView getParentView() {
        return this.parentView;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayoutManager(5, 2, new Insets(5, 5, 5, 5), -1, -1));
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MainPanel.add(ButtonPanel, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndQuitButton = new JButton();
        saveAndQuitButton.setText("Änderungen speichern und schließen");
        ButtonPanel.add(saveAndQuitButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Abbrechen");
        ButtonPanel.add(cancelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        MainPanel.add(separator1, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        GamesPane = new JTabbedPane();
        GamesPane.setTabPlacement(2);
        MainPanel.add(GamesPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        newGameButton = new JButton();
        newGameButton.setText("neues Spiel");
        MainPanel.add(newGameButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        MainPanel.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}

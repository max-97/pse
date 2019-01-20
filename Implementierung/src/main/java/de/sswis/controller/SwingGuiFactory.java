package de.sswis.controller;

import de.sswis.controller.handlers.*;
import de.sswis.view.*;

/**
 * Eine Fabrik zum Erzeugen von GUIs mit {@code Swing}. Diese Fabrik erzeugt Benutzeroberflächen, welche ausschließlich
 * aus Swing-Elementen bestehen. Die Benutzeroberflächen erhalten {@link java.awt.event.ActionListener}, die das
 * Verhalten der GUI-Elemente beschreiben.
 *
 * @author Max Braun
 * @see AbstractGuiFactory
 */
public class SwingGuiFactory implements AbstractGuiFactory {

    @Override
    public AbstractMainView createMainView() {
        AbstractMainView mainView = new MainView();

        mainView.addNewConfigMenuActionListener(new NewConfigurationViewHandler(this));
        mainView.addNewInitMenuActionListener(new NewInitializationViewHandler(this));
        mainView.addNewGameMenuActionListener(new NewGameViewHandler(this));
        mainView.addNewCombiStrategyMenuActionListener(new NewCombinedStrategyViewHandler(this));
        mainView.addNewStrategyMenuActionListener(new NewStrategyViewHandler(this));

        mainView.addManageConfigMenuActionListener(new ManageConfigurationsHandler(this));
        mainView.addManageInitMenuActionListener(new ManageInitializationHandler(this));
        mainView.addManageGameMenuActionListener(new ManageGamesHandler(this));
        mainView.addManageCombiStrategyMenuActionListener(new ManageCombinedStrategiesHandler(this));
        mainView.addManageStrategyMenuActionListener(new ManageStrategiesHandler(this));

        mainView.addSaveButtonActionlistener(new SaveAndQuitHandler(mainView));
        mainView.addShowButtonActionlistener(new ShowResultsHandler(mainView, this));
        mainView.addStartButtonActionlistener(new StartSimulationHandler(mainView));
        mainView.addStopActionListener(new StopSimulationHandler(mainView));

        return mainView;
    }

    @Override
    public AbstractShowResultView createShowResultView() {
        return null;
    }

    @Override
    public AbstractShowCompareView createCompareResultsView() {
        return null;
    }

    @Override
    public AbstractShowMultiResultView createMultiResultsView() {
        return null;
    }

    @Override
    public AbstractManageConfigurationsView createManageConfigurationsView() {
        return null;
    }

    @Override
    public AbstractManageInitializationsView createManageInitializationsView() {
        return null;
    }

    @Override
    public AbstractManageStrategiesView createManageStrategiesView() {
        return null;
    }

    @Override
    public AbstractManageCombinedStrategiesView createManageCombinedStrategiesView() {
        return null;
    }

    @Override
    public AbstractManageGamesView createManageGamesView() {
        return null;
    }

    @Override
    public AbstractManageResultsView createManageResultsView() {
        return null;
    }

    @Override
    public AbstractNewConfigurationView createNewConfigurationView() {
        return null;
    }

    @Override
    public AbstractNewInitializationView createNewInitializationView() {
        return null;
    }

    @Override
    public AbstractNewStrategyView createNewStrategyView() {
        return null;
    }

    @Override
    public AbstractNewCombinedStrategyView createNewCombinedStrategyView() {
        return null;
    }

    @Override
    public AbstractNewGameView createNewGameView() {
        return null;
    }
}

package de.sswis.view;

import de.sswis.controller.handlers.*;

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

        mainView.addManageConfigMenuActionListener(new ManageConfigurationsHandler(this, mainView));
        mainView.addManageInitMenuActionListener(new ManageInitializationHandler(this, mainView));
        mainView.addManageGameMenuActionListener(new ManageGamesHandler(this, mainView));
        mainView.addManageCombiStrategyMenuActionListener(new ManageCombinedStrategiesHandler(this, mainView));
        mainView.addManageStrategyMenuActionListener(new ManageStrategiesHandler(this, mainView));

        mainView.addSaveButtonActionlistener(new SaveAndQuitHandler(mainView));
        mainView.addShowButtonActionlistener(new ShowResultsHandler(mainView, this));
        mainView.addStartButtonActionlistener(new StartSimulationHandler(mainView));
        mainView.addStopActionListener(new StopSimulationHandler(mainView));

        return mainView;
    }

    @Override
    public AbstractShowResultView createShowResultView() {
        AbstractShowResultView showResultView = new ShowResultView();

        //TODO Methoden in showResultView definieren

        return showResultView;
    }

    @Override
    public AbstractShowCompareView createCompareResultsView() {
        AbstractShowCompareView showCompareView = new ShowCompareView();

        showCompareView.addCompareButtonActionlistener(new CompareResultsHandler(this));

        return showCompareView;
    }

    @Override
    public AbstractShowMultiResultView createMultiResultsView() {
        AbstractShowMultiResultView multiResultView = new ShowMultiResultView();

        multiResultView.addCompareButtonActionlistener(new CompareResultsHandler(this));

        return multiResultView;
    }

    @Override
    public AbstractManageConfigurationsView createManageConfigurationsView() {
        AbstractManageConfigurationsView manageConfigurationsView = new ManageConfigurationsView();

        manageConfigurationsView.addCancelButtonActionlistener(new CancelHandler(manageConfigurationsView));
        manageConfigurationsView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageConfigurationsView));
        manageConfigurationsView.addNewConfigurationButtonActionlistener(new NewConfigurationHandler(this));
        manageConfigurationsView.addEditConfigurationButtonActionlistener(
                new EditConfigurationHandler(this, manageConfigurationsView));
        manageConfigurationsView.addDeleteConfigurationButtonActionlistener(
                new DeleteConfigurationHandler(manageConfigurationsView));

        return manageConfigurationsView;
    }

    @Override
    public AbstractManageInitializationsView createManageInitializationsView() {
        AbstractManageInitializationsView manageInitializationsView = new ManageInitializationsView();

        manageInitializationsView.addCancelButtonActionlistener(new CancelHandler(manageInitializationsView));
        manageInitializationsView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageInitializationsView));
        manageInitializationsView.addNewInitButtonActionlistener(new NewInitializationHandler(this));
        manageInitializationsView.addEditInitButtonActionlistener(
                new EditInitializationHandler(this, manageInitializationsView));
        manageInitializationsView.addDeleteInitButtonActionlistener(
                new DeleteInitializationHandler(manageInitializationsView));

        return manageInitializationsView;
    }

    @Override
    public AbstractManageStrategiesView createManageStrategiesView() {
        AbstractManageStrategiesView manageStrategiesView = new ManageStrategiesView();

        manageStrategiesView.addCancelButtonActionlistener(new CancelHandler(manageStrategiesView));
        manageStrategiesView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageStrategiesView));
        manageStrategiesView.addNewMixedStrategyButtonActionlistener(new NewStrategyHandler(this));
        manageStrategiesView.addEditMixedStrategyButtonActionlistener(
                new EditStrategyHandler(this, manageStrategiesView));
        manageStrategiesView.addDeleteMixedStrategyButtonActionlistener(
                new DeleteStrategyHandler(manageStrategiesView));

        return manageStrategiesView;
    }

    @Override
    public AbstractManageCombinedStrategiesView createManageCombinedStrategiesView() {
        AbstractManageCombinedStrategiesView manageCombinedStrategiesView = new ManageCombinedStrategiesView();

        manageCombinedStrategiesView.addCancelButtonActionlistener(new CancelHandler(manageCombinedStrategiesView));
        manageCombinedStrategiesView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageCombinedStrategiesView));
        manageCombinedStrategiesView.addNewStrategyButtonActionlistener(new NewCombinedStrategyHandler(this));
        manageCombinedStrategiesView.addEditStrategyButtonActionlistener(
                new EditCombinedStrategyHandler(this, manageCombinedStrategiesView));
        manageCombinedStrategiesView.addDeleteStrategyButtonActionlistener(
                new DeleteCombinedStrategyHandler(manageCombinedStrategiesView));

        return manageCombinedStrategiesView;
    }

    @Override
    public AbstractManageGamesView createManageGamesView() {
        AbstractManageGamesView manageGamesView = new ManageGamesView();

        manageGamesView.addCancelButtonActionlistener(new CancelHandler(manageGamesView));
        manageGamesView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageGamesView));
        manageGamesView.addNewGameButtonActionlistener(new NewGameHandler(this));
        manageGamesView.addEditGameButtonActionlistener(new EditGameHandler(this, manageGamesView));
        manageGamesView.addDeleteGameButtonActionlistener(new DeleteGameHandler(manageGamesView));

        return manageGamesView;
    }

    @Override
    public AbstractManageResultsView createManageResultsView() {
        AbstractManageResultsView manageResultsView = new ManageResultsView();

        manageResultsView.addCancelButtonActionlistener(new CancelHandler(manageResultsView));
        manageResultsView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageResultsView));
        manageResultsView.addDeleteResultButtonActionlistener(new DeleteResultHandler(manageResultsView));

        return manageResultsView;
    }

    @Override
    public AbstractNewConfigurationView createNewConfigurationView() {
        AbstractNewConfigurationView newConfigurationView = new NewConfigurationView();

        newConfigurationView.addCancelButtonActionlistener(new CancelHandler(newConfigurationView));
        newConfigurationView.addFinishButtonActionlistener(new SaveConfigurationsHandler(newConfigurationView));

        return newConfigurationView;
    }

    @Override
    public AbstractNewInitializationView createNewInitializationView() {
        AbstractNewInitializationView newInitializationView = new NewInitializationView();

        newInitializationView.addCancelButtonActionlistener(new CancelHandler(newInitializationView));
        newInitializationView.addFinishButtonActionlistener(new SaveInitializationsHandler(newInitializationView));

        return newInitializationView;
    }

    @Override
    public AbstractNewStrategyView createNewStrategyView() {
        AbstractNewStrategyView newStrategyView = new NewStrategyView();

        newStrategyView.addCancelButtonActionlistener(new CancelHandler(newStrategyView));
        newStrategyView.addFinishButtonActionlistener(new SaveStrategiesHandler(newStrategyView));

        return newStrategyView;
    }

    @Override
    public AbstractNewCombinedStrategyView createNewCombinedStrategyView() {
        AbstractNewCombinedStrategyView newCombinedStrategyView = new NewCombinedStrategyView();

        newCombinedStrategyView.addCancelButtonActionlistener(new CancelHandler(newCombinedStrategyView));
        newCombinedStrategyView.addFinishButtonActionlistener(new SaveCombinedStrategiesHandler(newCombinedStrategyView));

        return newCombinedStrategyView;
    }

    @Override
    public AbstractNewGameView createNewGameView() {
        AbstractNewGameView newGameView = new NewGameView();

        newGameView.addCancelButtonActionlistener(new CancelHandler(newGameView));
        newGameView.addFinishButtonActionlistener(new SaveGamesHandler(newGameView));

        return newGameView;
    }
}

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

        manageConfigurationsView.addCancelButtonActionlistener(new CancleHandler(manageConfigurationsView));
        manageConfigurationsView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageConfigurationsView));
        manageConfigurationsView.addNewConfigurationButtonActionlistener(new NewConfigurationHandler(this));
        manageConfigurationsView.addEditConfigurationButtonActionlistener(new EditConfigurationHandler(this));
        manageConfigurationsView.addDeleteConfigurationButtonActionlistener(new DeleteConfigurationHandler(manageConfigurationsView));

        return manageConfigurationsView;
    }

    @Override
    public AbstractManageInitializationsView createManageInitializationsView() {
        AbstractManageInitializationsView manageInitializationsView = new ManageInitializationsView();

        manageInitializationsView.addCancelButtonActionlistener(new CancleHandler(manageInitializationsView));
        manageInitializationsView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageInitializationsView));
        manageInitializationsView.addNewInitButtonActionlistener(new NewInitializationHandler(this));
        manageInitializationsView.addEditInitButtonActionlistener(new EditInitializationHandler(this));
        manageInitializationsView.addDeleteInitButtonActionlistener(new DeleteInitializationHandler(manageInitializationsView));

        return manageInitializationsView;
    }

    @Override
    public AbstractManageStrategiesView createManageStrategiesView() {
        AbstractManageStrategiesView manageStrategiesView = new ManageStrategiesView();

        manageStrategiesView.addCancelButtonActionlistener(new CancleHandler(manageStrategiesView));
        manageStrategiesView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageStrategiesView));
        manageStrategiesView.addNewMixedStrategyButtonActionlistener(new NewStrategyHandler(this));
        manageStrategiesView.addEditMixedStrategyButtonActionlistener(new EditStrategyHandler(this));
        manageStrategiesView.addDeleteMixedStrategyButtonActionlistener(new DeleteStrategyHandler(manageStrategiesView));

        return manageStrategiesView;
    }

    @Override
    public AbstractManageCombinedStrategiesView createManageCombinedStrategiesView() {
        AbstractManageCombinedStrategiesView manageCombinedStrategiesView = new ManageCombinedStrategiesView();

        manageCombinedStrategiesView.addCancelButtonActionlistener(new CancleHandler(manageCombinedStrategiesView));
        manageCombinedStrategiesView.addSaveQuitButtonActionlistener(new SaveAndQuitHandler(manageCombinedStrategiesView));
        manageCombinedStrategiesView.addNewStrategyButtonActionlistener(new NewCombinedStrategyHandler(this));
        manageCombinedStrategiesView.addEditStrategyButtonActionlistener(new EditCombinedStrategyHandler(this));
        manageCombinedStrategiesView.addDeleteStrategyButtonActionlistener(new DeleteCombinedStrategyHandler(manageCombinedStrategiesView));

        return manageCombinedStrategiesView;
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

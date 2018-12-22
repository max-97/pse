package de.sswis.controller;

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
        return null;
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
    public AbstractNewGameView createNewGameView() {
        return null;
    }
}

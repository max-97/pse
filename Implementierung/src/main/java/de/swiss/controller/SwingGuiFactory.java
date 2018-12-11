package de.swiss.controller;

/**
 * Eine Fabrik zum Erzeugen von GUIs mit {@code Swing}. Diese Fabrik erzeugt Benutzeroberflächen, welche ausschließlich
 * aus Swingelementen bestehen. Die Benutzeroberflächen erhalten {@code ActionListener} aus
 * {@code de.swiss.controller.handler}.
 *
 * @author Max Braun
 * @see AbstractGuiFactory
 */
public class SwingGuiFactory implements AbstractGuiFactory {

    @Override
    public Object createMainView() {
        return null;
    }

    @Override
    public Object createShowResultView() {
        return null;
    }

    @Override
    public Object createManageConfigurationsView() {
        return null;
    }

    @Override
    public Object createManageInitializationsView() {
        return null;
    }

    @Override
    public Object createManageStrategiesView() {
        return null;
    }

    @Override
    public Object createManageGamesView() {
        return null;
    }

    @Override
    public Object createManageResultsView() {
        return null;
    }

    @Override
    public Object createNewConfigurationView() {
        return null;
    }

    @Override
    public Object createNewInitializationView() {
        return null;
    }

    @Override
    public Object createNewStrategyView() {
        return null;
    }

    @Override
    public Object createNewGameView() {
        return null;
    }
}

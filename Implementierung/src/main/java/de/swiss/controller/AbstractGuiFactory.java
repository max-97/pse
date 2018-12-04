package de.swiss.controller;

public interface AbstractGuiFactory {

    Object createMainView();

    Object createShowResultView();

    Object createEditConfigurationsView();

    Object createEditInitializationsView();

    Object createEditStrategiesView();

    Object createEditGamesView();

}

package de.sswis.view.model;

import java.util.List;

public class VMCombinedStrategy {
    private String name;
    private String description;

    private List<String> conditions;
    private List<String> strategies;


    public boolean isCorrect () {
        //TODO: implement me
        return false;
    }

    public void addStrategy(String strategyName, String condition) {}

    public void addStrategy(String strategyName, String condition, double x) {}

    public void addStrategy(String strategyName, String condition, int x) {}


}

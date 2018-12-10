package de.swiss.model;

public interface Strategy {
    public String getName();
    public String getDescription();
    public  Action calculateAction(Agent a1, Agent a2);
}

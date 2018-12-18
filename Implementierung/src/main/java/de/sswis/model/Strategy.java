package de.sswis.model;

public class Strategy {
    private String name;
    private String description;
    private CombinedStrategy[] combinedStrategies;
    private double[] probabilities;

    public Strategy(String name, String description, CombinedStrategy[] combinedStrategies, double[] probabilities) {

    }

    /*
    public String getName(){
        return name;
    }
    */

    /*
    public String getDescription() {
        return  description;
    }
    */

    public  Action calculateAction(Agent a1, Agent a2) {
        return null;
    }

}

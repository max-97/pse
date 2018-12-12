package de.swiss.model;


public class Game {

    private String name;
    private String description;
    private Tuple[][] payoffs;


    public Game(String name, String description, Tuple[][] payoffs) {

    }

    public Tuple getPayoffs(Action a1, Action a2) {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public class Tuple {
        public int x;
        public int y;

        public Tuple(int x, int y) {

        }

    }

    public void saveGame() {

    }
}

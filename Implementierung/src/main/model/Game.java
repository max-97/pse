package model;


public class Game {

    private String name;
    private String description;
    private Tuple[][] payoffs;


    public Game(String name, String description, Tuple[][] payoffs) {

    }

    public Tuple getPayoffs(int row, int column) {
        return payoffs[row][column];
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
}

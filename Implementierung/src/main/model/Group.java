package model;

public class Group {

    private int id;
    private String name;
    private Agent[] members;

    public Group(int id, String name) {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isMember(Agent agent) {
        return false;
    }

    public void addMember(Agent newMember) {

    }
}

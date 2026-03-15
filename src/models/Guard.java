package models;

public class Guard {

    private String guardId;
    private String name;

    public Guard(String guardId, String name) {
        this.guardId = guardId;
        this.name = name;
    }

    public String getGuardId() {
        return guardId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Guard ID: " + guardId + " | Name: " + name;
    }
}
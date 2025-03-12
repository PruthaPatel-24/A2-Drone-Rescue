package ca.mcmaster.se2aa4.island.teamXXX;

public class Map {
    int x_creek;
    int y_creek;
    int x_emergsite;
    int y_emergsite;
    Navigator location = new Navigator();

    public void foundCreek() {
        x_creek = location.getCurrentX();
        y_creek = location.getCurrentY();
    }

    public void foundEmergencySite() {
        x_emergsite = location.getCurrentX();
        y_emergsite = location.getCurrentY();
    }
}

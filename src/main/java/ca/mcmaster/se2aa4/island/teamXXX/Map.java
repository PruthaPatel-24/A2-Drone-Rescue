package ca.mcmaster.se2aa4.island.teamXXX;

public class Map {
    int x_creek;
    int y_creek;
    int x_emergsite;
    int y_emergsite;
    Boolean creek_found;
    Boolean emergency_site_found;
    Navigator location = new Navigator();

    public Boolean foundCreek() {
        x_creek = location.getCurrentX();
        y_creek = location.getCurrentY();
        return creek_found = true;
    }

    public Boolean foundEmergencySite() {
        x_emergsite = location.getCurrentX();
        y_emergsite = location.getCurrentY();
        return emergency_site_found = true;
    }

    public Boolean foundBoth() {
        if (creek_found && emergency_site_found) {
            return true;
        }
        else {
            return false;
        }
    }
}

package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {

    int x_creek;
    int y_creek;
    int x_emergsite;
    int y_emergsite;
    Boolean creek_found = false;
    Boolean emergency_site_found = false;
    Navigator location = new Navigator();
    private final Logger logger = LogManager.getLogger();

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
        if (creek_found == true && emergency_site_found == true) {
            return true;
        }
        else {
            return false;
        }
    }
}

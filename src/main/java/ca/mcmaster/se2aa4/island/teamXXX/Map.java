package ca.mcmaster.se2aa4.island.teamXXX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        if (x_creek != 0 && y_creek != 0) {
            return creek_found = true;
        }
        return creek_found = false;
    }

    public Boolean foundEmergencySite() {
        x_emergsite = location.getCurrentX();
        y_emergsite = location.getCurrentY();
        if (x_emergsite != 0 && y_emergsite != 0) {
            return emergency_site_found = true;
        }
        return emergency_site_found = false;
    }

    public Boolean foundBoth() {
        if (foundCreek() && foundEmergencySite()) {
            logger.info("Sites were found");
            return true;
        } else {
            logger.info("Sites were not found doo doo");
            return false;
        }
    }
}

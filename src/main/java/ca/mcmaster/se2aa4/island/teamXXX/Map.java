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

    public void foundCreek() {
        x_creek = location.getCurrentX();
        y_creek = location.getCurrentY();
        creek_found = true;
    }

    public void foundEmergencySite() {
        x_emergsite = location.getCurrentX();
        y_emergsite = location.getCurrentY();
        emergency_site_found = true;
    }

    public Boolean foundBoth() {
        logger.info("executed both");
        if (creek_found && emergency_site_found) {
            logger.info("creek and sites found");
            return true;
        }
        else {
            logger.info("both have not been found yet");
            return false;
        }
    }
}

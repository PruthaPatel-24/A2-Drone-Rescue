package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {
    int[][] creek_locations = new int[10][2];
    int creeks_found = 0;
    int x_emergsite;
    int y_emergsite;
    Boolean creek_found = false;
    Boolean emergency_site_found = false;
    Navigator location = Navigator.getInstance();
    private final Logger logger = LogManager.getLogger();

    public void foundCreek() {
        creek_locations[creeks_found][0] = location.getCurrentX();
        creek_locations[creeks_found][1] = location.getCurrentY();
        creeks_found++;
        logger.info("Creek locations: ");
        for (int i = 0; i < creeks_found; i++) {
            logger.info("Creek " + (i + 1) + ": X = " + creek_locations[i][0] + ", Y = " + creek_locations[i][1]);
        }
        creek_found = true;
    }

    public void foundEmergencySite() {
        x_emergsite = location.getCurrentX();
        y_emergsite = location.getCurrentY();
        emergency_site_found = true;
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

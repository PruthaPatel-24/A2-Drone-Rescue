package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.Math;


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

    public int[] closestCreek() {
        double min_distance = 
        Math.sqrt(Math.pow(y_emergsite - creek_locations[0][1], 2) + 
        Math.pow(x_emergsite - creek_locations[0][0], 2)); 
        //initially set distance to first creek in list
        int[] current_closest = creek_locations[0];
        for (int i = 1; i < creek_locations.length; i++ ) {
            int a = y_emergsite - creek_locations[i][1];
            int b = x_emergsite - creek_locations[i][0];
            double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b,2));
            if (c < min_distance) {
                min_distance = c;
                current_closest = creek_locations[i];
            }
        }
        return current_closest;
    }
}

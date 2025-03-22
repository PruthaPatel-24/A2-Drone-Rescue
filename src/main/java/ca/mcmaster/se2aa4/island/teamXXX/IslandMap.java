package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.HashMap;
import java.util.Map;

public class IslandMap {

    int[] site_location = new int[2];
    Map<String, int[]> creek_locations = new HashMap<>();
    String site_id;
    Boolean creek_found = false;
    Boolean emergency_site_found = false;
    Navigator location = Navigator.getInstance();

    public void foundCreek(String creek_id) {
        int[] coordinates = new int[]{location.getCurrentX(), location.getCurrentY()};
        creek_locations.put(creek_id, coordinates);
        creek_found = true;
    }

    public void foundEmergencySite() {
        site_location[0] = location.getCurrentX();
        site_location[1] = location.getCurrentY();
        emergency_site_found = true;
    }

    public Boolean foundBoth() {
        if (creek_found == true && emergency_site_found == true) {
            return true;
        } else {
            return false;
        }
    }

    public String closestCreek() {
        double minDistance = Double.MAX_VALUE;
        String creek_id = null;
        int x_creek = 0;
        int y_creek = 0;
        for (String creek : creek_locations.keySet()) {
            x_creek = creek_locations.get(creek)[1];
            y_creek = creek_locations.get(creek)[0];
            double distance = Math.sqrt(Math.pow(site_location[1] - x_creek, 2) + Math.pow(site_location[0] - y_creek, 2));
            if (distance < minDistance) {
                minDistance = distance;
                creek_id = creek;
            }
        }
        return "The closest creek to the emergency site is " + creek_id + " --> x: " + x_creek + " y: " + y_creek;
    }

    public Map getFoundCreeks() {
        return creek_locations;
    }

    public void setSiteID(String id) {
        site_id = id;
    }

    public String getSiteID() {
        return site_id;
    }

    public int[] getSiteCoordinates() {
        return site_location;
    }

}

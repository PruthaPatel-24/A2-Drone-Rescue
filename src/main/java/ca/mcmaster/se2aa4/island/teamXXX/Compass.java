package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Navigator {
    private int x; 
    private int y; 
    private int maxX;
    private int maxY; 
    private Compass d = Compass.East; //direction drone is facing

    private [][][] incr = {
        {{0, -1}, {+1, 0}, {0, +1}, {-1, 0}}, //move forward 
        {{-1,-1}, {+1, -1}, {+1, +1}, {-1, +1}}, // left turn 
        {{+1, -1}, {+1, +1}, {-1, +1}, {-1, -1}}  // right turn
    }
    public int[][] move(int currX, int currY, Drone d){
        x = currX + incr[m.ordinal()][direction.ordinal()][0];
        y = currY + incr[m.ordinal()][direction.ordinal()][1];

        d = Compass.values()[(d.ordinal() + m.ordinal()) % d.values().length];
    }
}
public enum Compass {North, East, South, West};
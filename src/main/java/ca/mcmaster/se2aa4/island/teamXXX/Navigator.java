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

    private Compass c = Compass.E; //direction drone is facing

    public void setMaxX(int i){
        maxX = i;
    }

    public void setMaxY(int i){
        maxY = i;
    }
 
    public Compass getC (){
        return c;
    }

    private int [][][] incr = {
        {{0, -1}, {+1, 0}, {0, +1}, {-1, 0}}, //move forward 
        {{-1,-1}, {+1, -1}, {+1, +1}, {-1, +1}}, // left turn 
        {{+1, -1}, {+1, +1}, {-1, +1}, {-1, -1}}  // right turn
    }; 
    
    public moveTo(int x, int y){
        //calls move to 
    }

    public void move(int xCurr, int yCurr, Movement m){
        x = x + incr[m.ordinal()][d.ordinal()][0];
        y = y + incr[m.ordinal()][d.ordinal()][1];

        c = Compass.values()[(d.ordinal() + m.ordinal()) % c.values().length];
    }

    public void goHome(){

    }




}
public enum Compass {N, E, S, W};
public enum Movement {Forward, Left, Right};
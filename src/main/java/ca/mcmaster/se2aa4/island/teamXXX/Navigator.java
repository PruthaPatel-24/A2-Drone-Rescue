package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    private int x = 0; 
    private int y = 0; 
    private int maxX;
    private int maxY; 
    private Compass c = Compass.N; //direction drone is facing
    private final Logger logger = LogManager.getLogger();

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
    
    public void moveTo(int x, int y){
        //calls move to 
    }

    public void move(Movement m){
        x = x + incr[m.ordinal()][c.ordinal()][0];
        y = y + incr[m.ordinal()][c.ordinal()][1];

        c = Compass.values()[(c.ordinal() + m.ordinal()) % c.values().length];
    }

    public int getCurrentX() {
        logger.info("Current x: " + x);
        return x;
    }

    public int getCurrentY() {
        logger.info("Current y: " + y);
        return y;
    }

    public void goHome(){

    }
}
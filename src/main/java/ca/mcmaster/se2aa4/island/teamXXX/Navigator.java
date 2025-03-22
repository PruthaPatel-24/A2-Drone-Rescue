package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    private int x = -1; 
    private int y = -1;
    private int maxX;
    private int maxY; 
    private Compass currentHeading = Compass.E; //direction drone is facing
    private final Logger logger = LogManager.getLogger();
    private static Navigator instance = null;

    public Navigator() {
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public void setDirection(Compass starting_direction) {
        logger.info("set current direction: " + currentHeading +  " to start direction: " + starting_direction);
        currentHeading = starting_direction;

    }

    public void setX(int i){
        x = i;
    }

    public void setY(int i){
        y = i;
    }

    public void setMaxX(int i){
        maxX = i;
    }

    public void setMaxY(int i){
        maxY = i;
    }

    public Compass getC (){
        return currentHeading;
    }

    private int [][][] incr = {
        {{0, -1}, {+1, 0}, {0, +1}, {-1, 0}}, //move forward 
        {{-1,-1}, {+1, -1}, {+1, +1}, {-1, +1}}, // left turn 
        {{+1, -1}, {+1, +1}, {-1, +1}, {-1, -1}}  // right turn
    }; 

    public void move(Movement m){
        x = x + incr[m.ordinal()][currentHeading.ordinal()][0];
        y = y + incr[m.ordinal()][currentHeading.ordinal()][1];
        
        if (m == Movement.Right){
            currentHeading = currentHeading.next();
        }
        else if (m == Movement.Left){
            currentHeading = currentHeading.previous();
        }
    }

    public int getCurrentX() {
        logger.info("Current x: " + x);
        return x;
    }

    public int getCurrentY() {
        logger.info("Current y: " + y);
        return y;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }


    public void setC(Compass c){
        this.currentHeading = c;
    }
}
package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Navigator {
    private int x = -1; 
    private int y = -1;
    private int maxX;
    private int maxY; 
    private Compass current_direction = Compass.E; //direction drone is facing
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
        logger.info("set current direction: " + current_direction +  " to start direction: " + starting_direction);
        current_direction = starting_direction;

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
        return current_direction;
    }

    private int [][][] incr = {
        {{0, -1}, {+1, 0}, {0, +1}, {-1, 0}}, //move forward 
        {{-1,-1}, {+1, -1}, {+1, +1}, {-1, +1}}, // left turn 
        {{+1, -1}, {+1, +1}, {-1, +1}, {-1, -1}}  // right turn
    }; 

    public void move(Movement m){
        x = x + incr[m.ordinal()][current_direction.ordinal()][0];
        y = y + incr[m.ordinal()][current_direction.ordinal()][1];
        logger.info("New x: " + x + " New y: " + y);

        current_direction = Compass.values()[(current_direction.ordinal() + m.ordinal()) % Compass.values().length];
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

    public void goHome(){

    }
}
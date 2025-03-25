package ca.mcmaster.se2aa4.island.teamXXX;
import static ca.mcmaster.se2aa4.island.teamXXX.Compass.*;

public class Navigator {
    private int x = -1; 
    private int y = -1;
    private int maxX;
    private int maxY; 
    private Compass currentHeading = Compass.N; //direction drone is facing
    private static Navigator instance = null;
    private int dimensionsFound = 0; 
    private int runningDimension = 0;

    private Navigator (){
    }

    public void setHeading(Compass heading){
        currentHeading = heading;
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public void setDirection(Compass starting_direction) {
        currentHeading = starting_direction;

    }

    public void incrementDimensionsFound (){
        this.dimensionsFound++;
    }
    public void updateRunningDimension(int i){
        this.runningDimension += i;
    }
    public int getDimensionsFound(){
        return dimensionsFound;
    }
    public int getRunningDimension(){
        return runningDimension;
    }
    public void setMainAxis(){
        if (currentHeading == N || currentHeading == S){
            maxY = runningDimension;
        }
        else{
            maxX = runningDimension;
        }
        runningDimension = 0;
    }
    public void setPerpendicularAxis(){
        if (currentHeading == E || currentHeading == W){
            maxY = runningDimension;
        }
        else{
            maxX = runningDimension;
        }
        runningDimension = 0;
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
        return x;
    }

    public int getCurrentY() {
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
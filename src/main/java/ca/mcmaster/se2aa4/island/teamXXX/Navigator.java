package ca.mcmaster.se2aa4.island.teamXXX;

public class Navigator {
    private int x; 
    private int y; 
    private int maxX;
    private int maxY; 
    private Compass c = Compass.N; //direction drone is facing

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
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    public void goHome(){

    }
}
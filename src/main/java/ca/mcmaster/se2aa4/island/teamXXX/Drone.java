package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Drone implements DroneActions {

    private Compass heading;
    private final Logger logger = LogManager.getLogger();
    JSONObject echoDecision = new JSONObject();
    JSONObject scanDecision = new JSONObject();
    JSONObject flyDecision = new JSONObject();
    JSONObject headingDecision = new JSONObject();
    JSONObject stopDecision = new JSONObject();
    JSONObject parameters = new JSONObject();
    Navigator n;
    EchoData echoDataLeft = new EchoData();
    EchoData echoDataRight = new EchoData();
    EchoData echoDataForward = new EchoData();

    public Drone(Compass heading) {
        this.heading = heading;
        this.n = Navigator.getInstance();
    }

    @Override
    public String fly() {
        n.move(Movement.Forward);
        flyDecision.put("action", "fly");
        return flyDecision.toString();
    }

    @Override
    public String turnLeft() {
        n.move(Movement.Left);
        heading = heading.previous();
        headingDecision.put("action", "heading");
        parameters.put("direction", heading.name());
        headingDecision.put("parameters", parameters);
        return headingDecision.toString();
    }

    @Override
    public String turnRight() {
        n.move(Movement.Right);
        heading = heading.next();
        headingDecision.put("action", "heading");
        parameters.put("direction", heading.name());
        headingDecision.put("parameters", parameters);
        return headingDecision.toString();
    }

    @Override
    public String scan() {
        scanDecision.put("action", "scan");
        return scanDecision.toString();
    }

    @Override
    public String stop() {
        logger.info("Stop action called");
        stopDecision.put("action", "stop");
        return stopDecision.toString();
    }

    public String echo(Compass direction) {
        echoDecision.put("action", "echo");
        parameters.put("direction", direction.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }

    public void fixX(){ //fix x-direction position to get to middle
        int xMiddle = n.getCurrentX()/2;
        // if too far left 
        if (n.getCurrentX() < n.getCurrentX()/2){
            if (heading != Compass.W){
                turnLeft();
                turnLeft();
            }
        }
        // if too far right 
        else if (n.getCurrentX() > n.getCurrentX()){
            if (heading != Compass.E){
                turnLeft();
                turnLeft();
            }
        }
        while (n.getCurrentX() != xMiddle){
            fly();
        }
    }

    public void fixY(){
    //second fix y-direction position 
        int yMiddle = n.getCurrentY()/2;
        // if too far up
        if (n.getCurrentY() < n.getCurrentY()/2){
            if (heading != Compass.N){
                turnLeft();
                turnLeft();
            }
        }
        // if too far down 
        else if (n.getCurrentX() > n.getCurrentX()){
            if (heading != Compass.S){
                turnLeft();
                turnLeft();
            }
        }
        while (n.getCurrentY() != yMiddle){
            fly();
        }
    }
    //one prints statement in here to remove too 
    public void goToMiddle() {
        int state = -1; 
        //finds dimension 1 
        
        findDimension(state);
        //find dimension 2
        findDimension(state);

        if (heading == Compass.N || heading == Compass.S){
            fixY();
            turnLeft();
            fixX();
        }
        else{
            fixX();
            turnLeft();
            fixY();
        }
        
        System.out.println("okay at middle, my coordinates are, x: " + String.valueOf(n.getCurrentX()) + String.valueOf(n.getCurrentY()));

    }

    public void findDimension(int state){  
        int dimension = 0;
        Compass startHeading = heading;
        state++; 
        if (state == 0){
            echo(heading.previous()); //echo with left wing
        } 
        else if (state == 1){
            echo(heading.next()); //echo with right wing
        }
        else if (state == 2 || state == 3){
            if (echoDataLeft.getLandDetected() == Terrain.OUT_OF_RANGE && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE){
                dimension = echoDataLeft.getRange() + echoDataRight.getRange();
                updateDimension(dimension, echoDataLeft.getRange(), Compass.values()[startHeading.ordinal()+1]);
                state = 5; // 5 means completed dimension find! 
            }
            else if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.GROUND){
                //can start spiral searching from here lowkey 
            }
            else{
                if (state == 2){
                    if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() != Terrain.OUT_OF_RANGE) {
                        dimension += echoDataRight.getRange() + 2;
                        turnLeft();
                    } else if (echoDataLeft.getLandDetected() != Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE) {
                        dimension += echoDataLeft.getRange() + 2;
                        turnRight();
                    }
                    echoDataForward.setLandDetected(Terrain.GROUND);
                }
                if (state == 3){
                    if (echoDataForward.getLandDetected() == Terrain.OUT_OF_RANGE){ //stop moving forward 
                        state = 5;
                    }
                    echo(heading);
                    dimension +=1; 
                    /*
                    while (echoDataForward.getLandDetected() != Terrain.OUT_OF_RANGE) { //KEEP MOVING FORWARD TILL FIND OTHER END 
                        echo(heading);
                        dimension += 1;
                    }
                    */
                }
                dimension += echoDataForward.getRange();
                updateDimension(dimension, echoDataForward.getRange(), startHeading);
            }
        }

    }
    public void updateDimension(int totalDimension, int currLocation, Compass startHeading){
        if (startHeading == Compass.N || startHeading == Compass.S){
            n.setMaxX(totalDimension);
            if (heading == Compass.N || heading == Compass.W){
                n.setX(currLocation);
            }
            else if (heading == Compass.E || heading == Compass.S){
                n.setX(totalDimension - currLocation);
            }
        }
        else{ //setting y values 
            n.setMaxY(totalDimension);
            if (heading == Compass.E || heading == Compass.N){
                n.setY(currLocation);
            }
            else if (heading == Compass.W || heading == Compass.S){
                n.setY(totalDimension - currLocation);
            }
        }
    }

    public void updateEchoData(int range, Terrain landDetected, Compass direction) {
        Movement wing = heading.compassToMovement(direction); //paramater takes in which direction (n,e,s, w) you echoes

        if (wing == Movement.Left) {
            echoDataLeft.setRange(range);
            echoDataLeft.setLandDetected(landDetected);
        } else if (wing == Movement.Right) {
            echoDataRight.setRange(range);
            echoDataRight.setLandDetected(landDetected);
        } else if (wing == Movement.Forward) {
            echoDataForward.setRange(range);
            echoDataForward.setLandDetected(landDetected);
        }
        
    }
}

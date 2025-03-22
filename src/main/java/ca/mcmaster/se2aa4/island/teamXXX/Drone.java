package ca.mcmaster.se2aa4.island.teamXXX;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import static ca.mcmaster.se2aa4.island.teamXXX.Terrain.*;

public class Drone implements DroneActions {

    private Compass heading;
    private final Logger logger = LogManager.getLogger();
    JSONObject echoDecision = new JSONObject();
    JSONObject scanDecision = new JSONObject();
    JSONObject flyDecision = new JSONObject();
    JSONObject headingDecision = new JSONObject();
    JSONObject stopDecision = new JSONObject();
    JSONObject parameters = new JSONObject();
    private Navigator n = new Navigator();
    private EchoData echoDataLeft = new EchoData();
    private EchoData echoDataRight = new EchoData();
    private EchoData echoDataForward = new EchoData();
    private Compass lastEcho; 

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

    public String echo(Compass direction) { //hello pls refactor spiral search to have moement takn in instead
        echoDecision.put("action", "echo");
        parameters.put("direction", direction.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }

    public String echo(Movement m) {
        Compass headingToEcho = n.getC().movementToCompass(m);
        echoDecision.put("action", "echo");
        parameters.put("direction", headingToEcho.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }
    
    public void updateRunningDimensionEchoLeft(){
        n.updateRunningDimension(echoDataLeft.range);
    }
    public void updateRunningDimensionEchoRight(){
        n.updateRunningDimension(echoDataRight.range);
    }
    public void updateRunningDimensionEchoForward(){
        n.updateRunningDimension(echoDataForward.range);
    }
    public void incrementRunningDimension(){
        n.updateRunningDimension(1);
    }

    public void updateDimension(boolean mainAxis){
        if (mainAxis){
            n.setMainAxis();
        }
        else{
            n.setPerpendicularAxis();
        }
        n.incrementDimensionsFound();
    }
    
    public boolean bothDimensionsFound(){
        return (n.getDimensionsFound() == 2);
    }
    boolean inCorrectCol = false;
    boolean inCorrectRow= false;

/*

    //variables for going to middle *************************
    public boolean inCorrectRow = false; 
    public boolean inCorrectCol = false; 
    int dimensionStart = 0;

*/


    public void updateEchoData(int range, Terrain landDetected) {
        Movement wing = heading.compassToMovement(lastEcho);
        logger.info("upating echo data");
        if (wing == Movement.Left) {
            logger.info("left wing");
            echoDataLeft.setRange(range);
            echoDataLeft.setLandDetected(landDetected);
        } else if (wing == Movement.Right) {
            logger.info("right wing");
            echoDataRight.setRange(range);
            echoDataRight.setLandDetected(landDetected);
        } else if (wing == Movement.Forward) {
            echoDataForward.setRange(range);
            echoDataForward.setLandDetected(landDetected);
        }
        logger.info("acknowedged results");
    }
    public FindDimensionState compareEchoData(){
        if (echoDataLeft.getLandDetected() == GROUND && echoDataRight.getLandDetected() == GROUND){
            return new BothGroundState();
        }
        else if(echoDataLeft.getLandDetected() == OUT_OF_RANGE && echoDataRight.getLandDetected() == OUT_OF_RANGE){
            return new BothOutOfRangeState();
        }
        else if (echoDataLeft.getLandDetected() == GROUND && echoDataRight.getLandDetected() == OUT_OF_RANGE){
            return new GroundOnlyOnLeftState();
        }
        else if(echoDataLeft.getLandDetected() == OUT_OF_RANGE && echoDataRight.getLandDetected() == GROUND){
            return new GroundOnlyOnRightState();
        }
        else{
            return null;
        }
    }

    public FindDimensionState forwardRangeDecision (){
        if (echoDataForward.landDetected == OUT_OF_RANGE){
            return new IncrementForwardDoneState();
        }
        else{
            return new IncrementForwardState();
        }
    }
    public FindDimensionState edgeFoundDecision (){
        if (echoDataForward.landDetected == OUT_OF_RANGE){
            return new UTurnPartOneState();
        }
        else{
            return new GoToEdgeState();
        }
    }
    public int getMaxX(){
        return n.getMaxX();
    }
    public int getMaxY(){
        return n.getMaxY();
    }

    /*
    //methods for going to middle *************************
    public String fixX(){ //fix x-direction position to get to middle
        logger.info("is getting into fix x");
        int xMiddle = n.getMaxX()/2;
        // if too far left 
        if (n.getCurrentX() < xMiddle){
            logger.info(" need to get to higher x coordinates");
            if (heading != Compass.E){
                return turnLeft();
            }
            else if (n.getCurrentY() == xMiddle - 2){
                inCorrectCol = true; 
                logger.info("found correct x");
                return turnLeft();
            }
        }
        // if too far right 
        else if (n.getCurrentX() > xMiddle){
            logger.info(" need to get to lower x coordinates");
            if (heading != Compass.W){
                return turnLeft();
            }
            else if (n.getCurrentX() == xMiddle+2){
                inCorrectCol = true;
                logger.info("found correct x");
                return turnLeft();
            }
        }
        else if (n.getCurrentX() == xMiddle){
            inCorrectCol = true;
        }
        logger.info("flying: " + String.valueOf(heading));
        return fly();
    }

    public String fixY(){ //should end with facing in x direction 
    //second fix y-direction position 
        logger.info("is getting into fix y");
        int yMiddle = n.getMaxY()/2;
        // if i am too far down
        if (n.getCurrentY() > yMiddle){
            logger.info(" need to get to lower y coordinates");
            if (heading != Compass.N){
                logger.info("turned to face north");
                return turnLeft(); //gonna happen twice 
            }
            else if (n.getCurrentY() == yMiddle + 2){
                inCorrectRow = true; 
                logger.info("found correct y");
                return turnLeft();
            }
        }
        // if i am too far up 
        else if (n.getCurrentY() < yMiddle){
            logger.info("i'm too far up, need to get to larger coordinates");
            if (heading != Compass.S){
                logger.info("turned to face south");
                return turnLeft(); //gonna happen twice 
            }
            else if (n.getCurrentY() == yMiddle - 2){
                inCorrectRow = true; 
                logger.info("found correct y");
                return turnLeft();
            }
        }
        else if (n.getCurrentY() == yMiddle){
            inCorrectRow = true;
        }
        logger.info("flying: " + String.valueOf(heading));
        return fly();
    }
    //one prints statement in here to remove too 
    int state = 10;
    public String goToMiddle() {
        logger.info("max x: " + String.valueOf(n.getMaxX() + " max y: " + String.valueOf(n.getMaxY())));
        logger.info("current x: " + String.valueOf(n.getCurrentX()) + ". current y: " + String.valueOf(n.getCurrentY()));
        logger.info(state);
        if (state == 10){ //if first round, figure out which direction to fix first (y or x)
            if (heading == Compass.N || heading == Compass.S){
                state = 11; //fix y then fix x
            }
            else{ //fix x then y 
                state = 14;
            } 
        }
        if (state == 11){
            if (inCorrectRow == true){
                state = 12;
            }
            else return fixY();
        }
        if (state == 12){
            if (inCorrectCol == true){
                logger.info("about to go to 15");
                state = 15;
            }
            
            else{
                logger.info("not going into 15");
                return fixX();
            }
            
        }
        if (state == 13){
            if (inCorrectCol == true){
                state = 14;
            }
            else return fixX();
        }
        if (state == 14){
            if (inCorrectRow == true){
                state = 15; 
            }
            else return fixY();
        }
        if (state == 15){
            //start spiral search 
            logger.info("okay at middle, my coordinates are, x: ");
            logger.info(n.getCurrentX());
            logger.info(n.getCurrentY());
            return stop();
        }
        return scan(); //should never actually reach 
        /*
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
    */
        
  

}

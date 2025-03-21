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

    public String echo(Compass direction) {
        lastEcho = direction;
        echoDecision.put("action", "echo");
        parameters.put("direction", direction.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }

    //variables for finding dimensions **********************
    public boolean skipTo7 = false; 
    int dimension = 0;
    Compass startHeading = heading;

    //variables for going to middle *************************
    public boolean inCorrectRow = false; 
    public boolean inCorrectCol = false; 

    //methods for finding dimensions******************
    public boolean getSkipTo7(){
        return skipTo7;
    }
    public void setSkipTo7(boolean b){
        skipTo7 = b;
    }

    public String findDimension(int state){  
        
        if (state == 0){
            logger.info (startHeading);
            return echo(heading.previous()); //echo with left wing
        } 
        else if (state == 1){
            return echo(heading.next()); //echo with right wing
        }
        else if (state == 2 || state == 4){//bug: wil have same start direction for ifs --fix! 
            if (echoDataLeft.getLandDetected() == Terrain.OUT_OF_RANGE && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE){
                dimension = echoDataLeft.getRange() + echoDataRight.getRange();
                updateDimension(dimension, echoDataLeft.getRange(), Compass.values()[startHeading.ordinal()+1]);
                skipTo7 = true; // 6 means completed dimension find! 
                logger.info("prutha stateeeeeeeee 2: should end cuz oor both sides");
                return scan();
            }
            else if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.GROUND){
                inCorrectRow = true; 
                inCorrectCol = true; 
                skipTo7 = true;
                logger.info("don't need dimensions, just spiral lowkey");
                return scan();
            }
            else{
                if (state == 2){
                    if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() != Terrain.OUT_OF_RANGE) {
                        dimension += echoDataRight.getRange() + 2;
                        echoDataForward.setLandDetected(Terrain.GROUND);
                        return turnLeft();
                    } else if (echoDataLeft.getLandDetected() != Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE) {
                        dimension += echoDataLeft.getRange() + 2;
                        echoDataForward.setLandDetected(Terrain.GROUND);
                        return turnRight();
                    }
                }
                else{ //state 4
                    if (echoDataForward.getLandDetected() == Terrain.OUT_OF_RANGE){ //stop moving forward 
                        startHeading=heading; 
                        dimension = 0; 
                        logger.info("current heading: ");
                        logger.info (startHeading);
                        skipTo7=true;
                    }
                    logger.info("incrmenting forward because forward.Range = ");
                    logger.info(echoDataForward.getRange());
                    dimension +=1;
                    return fly();
                    
                }
                dimension += echoDataForward.getRange();
                updateDimension(dimension, echoDataForward.getRange(), startHeading);
            }
        } else if (state == 3){
            logger.info("prutha state 3");
            dimension += echoDataForward.getRange();
            updateDimension(dimension, echoDataForward.getRange(), startHeading);
            return scan();
        }
        else if (state == 5){
            logger.info("just flied, so now go to echo new dimension");
            //need to go return to 4
            return echo(heading);
        }
        else if (state == 6){
            dimension += echoDataForward.getRange();
            updateDimension(dimension, echoDataForward.getRange(), startHeading);
            return scan();
        }
        else if (state == 7){
            logger.info("got into here");
            dimension = 0;
            echoDataForward.setLandDetected(Terrain.GROUND);
            //skipTo7 = false;
        }
        return scan();
       

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
        else{ //setting y values start heading wast east or west 
            n.setMaxY(totalDimension);
            if (heading == Compass.E || heading == Compass.N){
                n.setY(currLocation);
            }
            else if (heading == Compass.W || heading == Compass.S){
                n.setY(totalDimension - currLocation);
            }
        }
    }

    public void updateEchoData(int range, Terrain landDetected) {
        Movement wing = heading.compassToMovement(lastEcho);

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
        logger.info("acknowedged results");
    }

    public int getMaxX(){
        return n.getMaxX();
    }
    public int getMaxY(){
        return n.getMaxY();
    }

    //methods for going to middle *************************
    public String fixX(){ //fix x-direction position to get to middle
        int xMiddle = n.getCurrentX()/2;
        // if too far left 
        if (n.getCurrentX() < xMiddle){
            if (heading != Compass.E){
                return turnLeft();
            }
            else if (n.getCurrentY() == xMiddle - 2){
                inCorrectRow = true; 
                return turnLeft();
            }
        }
        // if too far right 
        else if (n.getCurrentX() > xMiddle){
            if (heading != Compass.W){
                return turnLeft();
            }
            else if (n.getCurrentX() == xMiddle+2){
                inCorrectRow = true;
                return turnLeft();
            }
        }
        return fly();
    }

    public String fixY(){ //should end with facing in x direction 
    //second fix y-direction position 
        int yMiddle = n.getMaxY()/2;
        // if i am too far down
        if (n.getCurrentY() > yMiddle){
            if (heading != Compass.N){
                return turnLeft(); //gonna happen twice 
            }
            else if (n.getCurrentY() == yMiddle + 2){
                inCorrectRow = true; 
                return turnLeft();
            }
        }
        // if i am too far up 
        else if (n.getCurrentY() < yMiddle){
            if (heading != Compass.S){
                return turnLeft(); //gonna happen twice 
            }
            else if (n.getCurrentY() == yMiddle - 2){
                inCorrectRow = true; 
                return turnLeft();
            }
        }
        return fly();
    }
    //one prints statement in here to remove too 
    int state = 10;
    public String goToMiddle() {

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
                state = 15;
            }
            else return fixX();
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
            System.out.println("okay at middle, my coordinates are, x: " + String.valueOf(n.getCurrentX()) + String.valueOf(n.getCurrentY()));
            return scan();
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
   

}

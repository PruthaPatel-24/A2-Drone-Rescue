package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions{
    private Compass heading;
    JSONObject echoDecision = new JSONObject();
    JSONObject scanDecision = new JSONObject();
    JSONObject flyDecision = new JSONObject();
    JSONObject headingDecision = new JSONObject();
    JSONObject stopDecision = new JSONObject();
    JSONObject parameters = new JSONObject();
    Navigator n = new Navigator();
    EchoData echoDataLeft = new EchoData();
    EchoData echoDataRight = new EchoData();
    EchoData echoDataForward = new EchoData();

    public Drone(Compass heading) {
        this.heading = heading;
    }

    @Override
    public String fly() {
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
        stopDecision.put("action", "stop");
        return stopDecision.toString();
    }

    public String echo(Compass direction) {
        echoDecision.put("action", "echo");
        parameters.put("direction", direction.name());
        echoDecision.put("parameters", parameters);
        return echoDecision.toString();
    }

    public void goToMiddle(){
        echo(heading.previous()); //echo with left wing 
        echo(heading.next()); //echo with right wing 
        int width = 0; 

        //finds width 
        if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() != Terrain.OUT_OF_RANGE){
            width += echoDataRight.getRange() + 2;
            turnLeft();
            echoDataForward.setLandDetected(Terrain.GROUND);
            while (echoDataForward.getLandDetected() != Terrain.OUT_OF_RANGE){ //KEEP MOVING FORWARD TILL FIND OTHER END 
                echo(heading);
                width +=1; 
            }
            width += echoDataForward.getRange();
        }
        else if (echoDataLeft.getLandDetected() != Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE){
            width += echoDataLeft.getRange() + 2;
            turnRight();
            echoDataForward.setLandDetected(Terrain.GROUND);
            while (echoDataForward.getLandDetected() != Terrain.OUT_OF_RANGE){ //KEEP MOVING FORWARD TILL FIND OTHER END 
                echo(heading);
                width +=1; 
            }
            width += echoDataForward.getRange();
        }
        else{
            width = echoDataLeft.getRange() + echoDataRight.getRange();
        }
        
    }
    public void updateEchoData(int range, Terrain landDetected, Movement direction) {
        if (direction == Movement.Left){
            echoDataLeft.setRange(range);
            echoDataLeft.setLandDetected(landDetected); 
        }
        else if (direction == Movement.Right){
            echoDataRight.setRange(range);
            echoDataRight.setLandDetected(landDetected); 
        }
        else if (direction == Movement.Forward){
            echoDataForward.setRange(range);
            echoDataForward.setLandDetected(landDetected); 
        }
    }
}

package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions {

    private Compass heading;
    JSONObject decision = new JSONObject();
    JSONObject parameters = new JSONObject();
    Navigator n = new Navigator();
    EchoData echoDataLeft = new EchoData();
    EchoData echoDataRight = new EchoData();
    EchoData echoDataForward = new EchoData();

    public Drone(Compass heading) {
        this.heading = heading;
    }

    @Override
    public void fly() {
        decision.put("action", "fly");
    }

    @Override
    public void turnLeft() {
        n.move(Movement.Left);
        heading = heading.previous();
        parameters.put("direction", heading.name());
    }

    @Override
    public void turnRight() {
        n.move(Movement.Right);
        heading = heading.next();
        parameters.put("direction", heading.name());
    }

    @Override
    public void scan() {
        decision.put("action", "scan");
    }

    @Override
    public void stop() {
        decision.put("action", "stop");
    }

    public void echo(Compass direction) {
        decision.put("action", "echo");
        parameters.put("direction", direction.name());

    }

    public void goToMiddle() {
        echo(heading.previous()); //echo with left wing 
        echo(heading.next()); //echo with right wing 
        int width = 0;

        //finds width 
        if (echoDataLeft.getLandDetected() == Terrain.GROUND && echoDataRight.getLandDetected() != Terrain.OUT_OF_RANGE) {
            width += echoDataRight.getRange() + 2;
            turnLeft();
            echoDataForward.setLandDetected(Terrain.GROUND);
            while (echoDataForward.getLandDetected() != Terrain.OUT_OF_RANGE) { //KEEP MOVING FORWARD TILL FIND OTHER END 
                echo(heading);
                width += 1;
            }
            width += echoDataForward.getRange();
        } else if (echoDataLeft.getLandDetected() != Terrain.GROUND && echoDataRight.getLandDetected() == Terrain.OUT_OF_RANGE) {
            width += echoDataLeft.getRange() + 2;
            turnRight();
            echoDataForward.setLandDetected(Terrain.GROUND);
            while (echoDataForward.getLandDetected() != Terrain.OUT_OF_RANGE) { //KEEP MOVING FORWARD TILL FIND OTHER END 
                echo(heading);
                width += 1;
            }
            width += echoDataForward.getRange();
        } else {
            width = echoDataLeft.getRange() + echoDataRight.getRange();
        }

    }

    public void updateEchoData(int range, Terrain landDetected, Movement direction) {
        if (direction == Movement.Left) {
            echoDataLeft.setRange(range);
            echoDataLeft.setLandDetected(landDetected);
        } else if (direction == Movement.Right) {
            echoDataRight.setRange(range);
            echoDataRight.setLandDetected(landDetected);
        } else if (direction == Movement.Forward) {
            echoDataForward.setRange(range);
            echoDataForward.setLandDetected(landDetected);
        }
    }
}

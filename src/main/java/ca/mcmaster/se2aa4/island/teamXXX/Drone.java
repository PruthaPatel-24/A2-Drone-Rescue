package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions{
    private Compass heading;
    JSONObject decision = new JSONObject();
    JSONObject parameters = new JSONObject();
    Navigator n = new Navigator();
    EchoData echoData = new EchoData();


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

    public void updateEchoData(int range, Terrain landDetected) {
        echoData.setRange(range);
        echoData.setLandDetected(landDetected);
    }
}

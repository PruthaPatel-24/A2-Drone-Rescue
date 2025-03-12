package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions{
    private Compass heading;
    JSONObject decision = new JSONObject();
    JSONObject parameters = new JSONObject();
    Navigator n = new Navigator();

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
        parameters.put("direction", heading.previous());
    }

    public void turnRight() {
        n.move(Movement.Right);
        parameters.put("direction", heading.next());
    }

    @Override
    public void scan() {
        decision.put("action", "scan");
    }

    @Override
    public void stop() {
        decision.put("action", "stop");
    }
}

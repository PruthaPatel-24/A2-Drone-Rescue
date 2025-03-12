package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions{
    private String heading;
    JSONObject decision = new JSONObject();
    JSONObject parameters = new JSONObject();

    public Drone(String heading, JSONObject decision, JSONObject parameters) {
        this.heading = heading;
        this.decision = decision;
        this.parameters = parameters;
    }

    @Override
    public void fly() {
        decision.put("action", "fly");
    }

    @Override
    public void changeDirection() {
        parameters.put("direction", n.move());
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

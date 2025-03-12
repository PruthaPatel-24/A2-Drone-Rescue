package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements DroneActions{
    private int battery_life;
    private String heading;
    private JSONObject decision;
    private JSONObject parameters;

    public Drone(int batteryBudget, String heading, JSONObject decision, JSONObject parameters) {
        this.battery_life = batteryBudget;
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
        if ()
        decision.put("action", "scan");
    }

    @Override
    public void stop() {
        decision.put("action", "stop");
    }
}

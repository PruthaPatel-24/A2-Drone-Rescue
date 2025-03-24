package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public class Drone implements BasicDroneActions{

    private JSONObject flyDecision = new JSONObject();
    private JSONObject scanDecision = new JSONObject();
    private JSONObject stopDecision = new JSONObject();
    protected Navigator n = Navigator.getInstance();

    @Override
    public String fly() {
        n.move(Movement.Forward);
        flyDecision.put("action", "fly");
        return flyDecision.toString();
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
    
}

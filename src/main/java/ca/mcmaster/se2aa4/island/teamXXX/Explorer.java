package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    private int x = 0;
    private int y = 0;
    private int range = -1;
    private boolean exploredEast = false;
    private boolean exploredSouth = false;

    @Override
    public void initialize(String s) {
        //'s' parameter is the simulation startup info as a JSON string
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        Battery current_battery_life = new Battery(batteryLevel);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        public Drone drone = new Drone(direction);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();
        
        if (!exploredEast && range == -1) {
            decision.put("action", "scan");
            //parameters.put("direction", "E");  // Start by exploring East
        } 
        else if (!exploredEast) {
            x = range;
            exploredEast = true;

            decision.put("action", "echo");
            parameters.put("direction", "S"); // Move South next
        } 
        else if (!exploredSouth) {
            y = range;
            exploredSouth = true;

            decision.put("action", "stop");
        } 
        else {
            decision.put("action", "stop");
        }
        logger.info("Current coordinates: x = {}, y = {}", x, y);
        
        decision.put("parameters", parameters);
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");

        logger.info("Additional information received: {}", extraInfo);

        if (response.getJSONObject("extras").has("range")) {
            range = response.getJSONObject("extras").getInt("range");
            logger.info("** Updated range value: {}", range);
        }

        if (response.getJSONObject("extras").has("creeks")) {
            logger.info("Creek Found!!");
            drone.foundCreek();
        }

    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}

package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    int i = 0;
    Battery current_battery_life;
    private boolean batteryIsLow = false;
    Drone drone;
    Map map = new Map();
    int range;
    SpiralSearch search = new SpiralSearch();

    @Override
    public void initialize(String s) {
        //'s' parameter is the simulation startup info as a JSON string
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        current_battery_life = new Battery(batteryLevel);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        drone = new Drone(Compass.valueOf(direction));
        Execution execution = new Execution(drone);

    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        i++;
        if (i < 500) {
            logger.info("calling spiral search algorithm");
            String searchValue = search.spiralSearchAlgorithm();
            logger.info(searchValue);
            return searchValue;
        }
        else {
            logger.info("stopping");
            decision.put("action", "stop");
            return decision.toString();
        }
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
        
        //batteryIsLow = current_battery_life.reduce_battery(cost);
        //if (batteryIsLow) {
        //    drone.stop();
        //}

        if (response.getJSONObject("extras").has("range")) {
            range = response.getJSONObject("extras").getInt("range");
            logger.info("** Updated range value: {}", range);
            
        }

        if (response.getJSONObject("extras").has("creeks")) {
            logger.info("Creek Found!!");
            //map.foundCreek();
        }

        if (response.getJSONObject("extras").has("sites")) {
            logger.info("Emergency Site Found!!");
            //map.foundEmergencySite();
        }

        if (extraInfo.has("found")) {
            String foundValue = extraInfo.getString("found");
            if ("OUT_OF_RANGE".equals(foundValue)) {
                range = response.getJSONObject("extras").getInt("range");
                /*
                 * we can have another method in drone like checkEcho() or smth that will tell 
                 * us to go in the other direction if the echo shows out of range 
                 * within a certain distance
                 * 
                 * The function will probably take the range as a parameter
                 */
                logger.info("** the drone is out of range");
            }
            //HELP NEEDED HERE: THIRD PARAMATER IS CURRENTLY HARD CODED -- HOW TO RETREIVE ECHO DIRECTION FROM DEICSION METHOD?
            //drone.updateEchoData(range, Terrain.valueOf(foundValue), Movement.Forward/*echoDirection (l, r, forward - of movement type)*/);
        }
        //Sites and creeks are returned in an array with the site and creek ID which we might also need to store
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}

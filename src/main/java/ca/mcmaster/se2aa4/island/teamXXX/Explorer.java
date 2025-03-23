package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    int i = 0;
    Battery current_battery_life;
    boolean batteryIsLow = false;
    Drone drone;
    IslandMap map = new IslandMap();
    int range;
    Navigator n = Navigator.getInstance();
    FindDimensionState FDState = new StartState();
    SpiralSearch search = new SpiralSearch(drone);

    //variables for finding dimensions of map 
    //int state = -1;
    //int dimensionsFound = 0; 

    @Override
    public void initialize(String s) {
        //'s' parameter is the simulation startup info as a JSON string
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        current_battery_life = new Battery(batteryLevel);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        drone = new Drone();
        n.setHeading(Compass.valueOf(direction));
        search = new SpiralSearch(drone);

    }

    //variables NEWEST


    @Override
    public String takeDecision() {
        
        FDState = FDState.nextState();
        logger.info("my state is: " + FDState.getClass().getName());
        return FDState.execute(drone);
        
        /*
        JSONObject decision = new JSONObject();
        if (!map.foundBoth()) {
            return search.spiralSearchAlgorithm();
        }
        logger.info("stopping");
        JSONObject parameters = new JSONObject();
        if (state == 6){
            state = 3;
        }
        else if (drone.getSkipTo7()){
            state = 7; 
        }
        if (state == 7 && dimensions_found <2){
            dimensions_found++;
            drone.setSkipTo7(false);
            state =-1;
            return drone.scan();
        }
        else if (state<7 && dimensions_found < 2){
            state++;
            String executeCommand = drone.findDimension(state); 
            logger.info("state");
            logger.info(state);
            logger.info("dimensions found");
            logger.info(dimensions_found);
            return executeCommand;
        }
        else if (dimensions_found == 2) { // or state = 8
            logger.info("my state number right now is");
            logger.info(state);
            logger.info("my width and length are: ");
            logger.info("dimensions found");
            logger.info(dimensions_found);
            logger.info(drone.getMaxX());
            logger.info(drone.getMaxY());
            dimensions_found++;
            return drone.stop();
            
            //decision.put("action", "stop");
            //return decision.toString();
        }
            */

        //decision.put("action", "stop");
        //return decision.toString();

        //now go to middle!! 
        //return drone.goToMiddle();
    }


    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));

        Integer cost = response.getInt("cost");
        //logger.info("The cost of the action was {}", cost);

        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);

        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        batteryIsLow = current_battery_life.reduce_battery(cost);
        if (batteryIsLow) {
            drone.stop();
        }

        if (response.getJSONObject("extras").has("creeks") && response.getJSONObject("extras").getJSONArray("creeks").length() > 0) {
            logger.info("Creek Found!!");
            JSONArray creeks = response.getJSONObject("extras").getJSONArray("creeks");
            String creek_id = creeks.getString(0);
            map.foundCreek(creek_id);
        }

        if (response.getJSONObject("extras").has("sites") && response.getJSONObject("extras").getJSONArray("sites").length() > 0) {
            logger.info("Emergency Site Found!!");
            JSONArray sites = response.getJSONObject("extras").getJSONArray("sites");
            String site_id = sites.getString(0);
            map.foundEmergencySite();
            map.setSiteID(site_id);
        }

        if (extraInfo.has("found")) {
            String foundValue = extraInfo.getString("found");
            range = response.getJSONObject("extras").getInt("range");
            logger.info("updating");
            drone.updateEchoData(range, Terrain.valueOf(foundValue));
        }
    }

    @Override
    public String deliverFinalReport() {
        FinalReport report = new FinalReport(map);
        String reportClosestCreek = report.finalReport();
        logger.info("Generated final report: {}", reportClosestCreek);
        return reportClosestCreek;
    }

}

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
    SpiralSearch search;
    Navigator navigator = Navigator.getInstance();

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

        navigator.setDirection(Compass.valueOf(direction));
        drone = new Drone(Compass.valueOf(direction));
        search = new SpiralSearch(drone);

    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        if (!map.foundBoth()) {
            return search.spiralSearchAlgorithm();
        }
        logger.info("stopping");
        decision.put("action", "stop");
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));

        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);

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
            if ("OUT_OF_RANGE".equals(foundValue)) {
                range = response.getJSONObject("extras").getInt("range");
                logger.info("** the drone is out of range");
            }
            //drone.updateEchoData(range, Terrain.valueOf(foundValue), Movement.Forward/*echoDirection (l, r, forward - of movement type)*/);
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

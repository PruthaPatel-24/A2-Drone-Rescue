package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.FindDimensionState;
import ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates.StartFDState;
import ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates.GoToMiddleState;
import ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates.StartGTMState;
import static ca.mcmaster.se2aa4.island.teamXXX.Machine.FIND_DIMENSION;
import static ca.mcmaster.se2aa4.island.teamXXX.Machine.GO_TO_MIDDLE;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.SpiralState;
import ca.mcmaster.se2aa4.island.teamXXX.SpiralSearchStates.StartSpiralSearch;
import eu.ace_design.island.bot.IExplorerRaid;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    Battery current_battery_life;
    boolean batteryIsLow = false;
    RescueDrone drone;
    IslandMap map = IslandMap.getInstance();
    int range;
    Navigator n = Navigator.getInstance();

    //variables for finding dimensions of map 
    //int state = -1;
    //int dimensionsFound = 0; 
    private FindDimensionState FDState = new StartFDState();
    private GoToMiddleState GTMState = new StartGTMState();
    private SpiralState SpiralState = new StartSpiralSearch();
    private Machine currentMachine = FIND_DIMENSION;

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

        drone = new RescueDrone();
        n.setHeading(Compass.valueOf(direction));

    }

    @Override
    public String takeDecision() {
        logger.info("my current machine: " + currentMachine);
        if (currentMachine == FIND_DIMENSION) {
            FDState = FDState.nextState();
            if (FDState == null) {
                currentMachine = currentMachine.next();
                return drone.scan();
            } else {
                return FDState.execute(drone);
            }

        } else if (currentMachine == GO_TO_MIDDLE) {
            GTMState = GTMState.nextState();
            if (GTMState == null) {
                currentMachine = currentMachine.next();
                return drone.scan();
            } else {
                return GTMState.execute(drone);
            }

        } else {
            SpiralState = SpiralState.nextState();
            if (SpiralState == null || batteryIsLow == true) {
                return drone.stop();
            } else {
                return SpiralState.execute(drone);
            }
        }

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

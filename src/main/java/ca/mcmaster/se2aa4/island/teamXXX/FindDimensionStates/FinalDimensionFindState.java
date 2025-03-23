package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class FinalDimensionFindState implements FindDimensionState {
    private final Logger logger = LogManager.getLogger();
    public String execute(Drone d){
        logger.info("X width: " + d.getMaxX() + " y width: " + d.getMaxY());

        return d.scan(); 
    }

    public FindDimensionState nextState(){
        return null;
    }

}


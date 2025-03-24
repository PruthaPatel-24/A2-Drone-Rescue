package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FinalDimensionFindState implements FindDimensionState {

    private final Logger logger = LogManager.getLogger();

    @Override
    public String execute(RescueDrone d) {
        logger.info("X width: " + d.getMaxX() + " y width: " + d.getMaxY());

        return d.scan();
    }

    @Override
    public FindDimensionState nextState() {
        return null;
    }

}

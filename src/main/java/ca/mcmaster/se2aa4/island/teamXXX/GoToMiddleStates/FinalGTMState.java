package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FinalGTMState implements GoToMiddleState { 
    private final Logger logger = LogManager.getLogger();
    public String execute(Drone d){
        logger.info("my current coordinates are x: " + d.getCurrentX() + "y: " + d.getCurrentY());
        return d.stop();
    }
    public GoToMiddleState nextState(){
        return new StartGTMState();
    }
}


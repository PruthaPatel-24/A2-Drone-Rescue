package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FinalGTMState implements GoToMiddleState { 
    private final Logger logger = LogManager.getLogger();
    public String execute(Drone d){
        int middleX = d.getMaxX()/2; //don't need this variable after
        int middleY = d.getMaxY()/2; //doon't need this variabele after
        logger.info("ccccurrent x: " + d.getCurrentX() + "middle x: " + middleX);
        logger.info("ccccurrent y: " + d.getCurrentY() + "middle y: " + middleY);
        return d.scan();
    }
    public GoToMiddleState nextState(){
        return null;
    }
}


package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FinalGTMState implements GoToMiddleState {

    private final Logger logger = LogManager.getLogger();

    @Override
    public String execute(RescueDrone d) {
        int middleX = d.getMaxX() / 2; //don't need this variable after
        int middleY = d.getMaxY() / 2; //doon't need this variabele after
        return d.scan();
    }

    @Override
    public GoToMiddleState nextState() {
        return null;
    }
}

package ca.mcmaster.se2aa4.island.teamXXX.GoToMiddleStates;

import org.apache.logging.log4j.LogManager;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class FinalGTMState implements GoToMiddleState {

    @Override
    public String execute(RescueDrone d) {
        return d.scan();
    }

    @Override
    public GoToMiddleState nextState() {
        return null;
    }
}

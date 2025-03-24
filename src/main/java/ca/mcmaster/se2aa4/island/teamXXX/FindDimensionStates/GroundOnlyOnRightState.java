package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class GroundOnlyOnRightState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        d.updateRunningDimensionEchoLeft();
        //adding two increments here to account for the two spaces skipped when turning 
        d.incrementRunningDimension();
        d.incrementRunningDimension();
        return d.turnRight();
    }

    @Override
    public FindDimensionState nextState() {
        return new EchoForwardState();
    }

}

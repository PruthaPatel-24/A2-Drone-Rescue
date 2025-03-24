package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class GroundOnlyOnLeftState implements FindDimensionState {

    @Override
    public String execute(Drone d) {
        d.updateRunningDimensionEchoRight();
        //adding two increments here to account for the two spaces skipped when turning 
        d.incrementRunningDimension();
        d.incrementRunningDimension();
        return d.turnLeft();
    }

    @Override
    public FindDimensionState nextState() {
        return new EchoForwardState();
    }

}

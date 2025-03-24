package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class BothGroundState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        return d.turnLeft(); // just pick a random direction 
    }

    @Override
    public FindDimensionState nextState() {
        return new LookForEdgeState();
    }

}

package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class UTurnPartOneState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        d.updateRunningDimensionEchoForward();
        d.incrementRunningDimension();
        d.refreshEchoData();
        return d.turnLeft();
    }

    @Override
    public FindDimensionState nextState() {
        return new UTurnPartTwoState();

    }

}

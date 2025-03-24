package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class UTurnPartTwoState implements FindDimensionState {

    @Override
    public String execute(RescueDrone d) {
        return d.turnLeft();
    }

    @Override
    public FindDimensionState nextState() {
        return new EchoForwardState();

    }

}

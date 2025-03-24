package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class UTurnPartTwoState implements FindDimensionState {

    @Override
    public String execute(Drone d) {
        return d.turnLeft();
    }

    @Override
    public FindDimensionState nextState() {
        return new EchoForwardState();

    }

}

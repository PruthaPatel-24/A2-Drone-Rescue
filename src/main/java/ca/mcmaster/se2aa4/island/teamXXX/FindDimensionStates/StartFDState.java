package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class StartFDState implements FindDimensionState {

    @Override
    public String execute(Drone d) {
        return d.scan();
    }

    @Override
    public FindDimensionState nextState() {
        return new EchoLeftState();
    }

}

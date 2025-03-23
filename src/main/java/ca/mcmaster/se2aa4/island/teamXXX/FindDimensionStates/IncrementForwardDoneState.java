package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class IncrementForwardDoneState implements FindDimensionState {

    FindDimensionState nextState = new StartFDState();

    @Override
    public String execute(Drone d) {
        d.updateRunningDimensionEchoForward();
        d.updateDimension(true);

        if (d.bothDimensionsFound()) {
            nextState = new FinalDimensionFindState(); //first state of middle machine! 
        } else {
            nextState = new StartFDState();
        }
        return d.scan();
    }

    @Override
    public FindDimensionState nextState() {
        return nextState;
    }

}

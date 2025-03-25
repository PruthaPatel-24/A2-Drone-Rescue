package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class IncrementForwardDoneState implements FindDimensionState {

    FindDimensionState nextState = new StartFDState();

    @Override
    public String execute(RescueDrone d) {
        d.updateRunningDimensionEchoForward();
        d.updateDimension(true);

        if (d.bothDimensionsFound()) {
            nextState = new FinalDimensionFindState(); //first state of middle machine! 
        } else {
            nextState = new StartFDState();
        }
        return null;
    }

    @Override
    public FindDimensionState nextState() {
        return nextState;
    }

}

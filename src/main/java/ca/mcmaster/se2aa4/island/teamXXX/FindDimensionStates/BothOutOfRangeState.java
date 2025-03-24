package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.Drone;

public class BothOutOfRangeState implements FindDimensionState {

    FindDimensionState nextState = new StartFDState();

    @Override
    public String execute(Drone d) {
        //add echo left and echo right + 1 to the axis we are NOT facing 

        d.updateRunningDimensionEchoLeft();
        d.updateRunningDimensionEchoRight();
        d.incrementRunningDimension();
        d.updateDimension(false);

        if (d.bothDimensionsFound()) {
            nextState = new FinalDimensionFindState(); //first state of middle
        } else {
            nextState = new StartFDState();
        }
        return d.turnLeft();
    }

    @Override
    public FindDimensionState nextState() {
        return nextState;
    }

}

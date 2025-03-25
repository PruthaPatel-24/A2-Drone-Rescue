package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class IncrementForwardState implements FindDimensionState {

    FindDimensionState nextState = new StartFDState();

    @Override
    public String execute(RescueDrone d) {
        if (d.forwardRangeDecision()) {
            d.incrementRunningDimension();
            nextState = new EchoForwardState();
            return d.fly();
        } else {
            nextState = new IncrementForwardDoneState();
            return null;

        }
    }

    @Override
    public FindDimensionState nextState() {
        //return new EchoForwardState();
        return nextState;
    }

}

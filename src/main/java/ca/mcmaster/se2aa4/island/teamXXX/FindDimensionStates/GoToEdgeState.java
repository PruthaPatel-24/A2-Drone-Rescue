package ca.mcmaster.se2aa4.island.teamXXX.FindDimensionStates;

import ca.mcmaster.se2aa4.island.teamXXX.RescueDrone;

public class GoToEdgeState implements FindDimensionState {

    FindDimensionState nextState = new StartFDState();

    @Override
    public String execute(RescueDrone d) {
        if (d.edgeFoundDecision()) {
            nextState = new LookForEdgeState();
            return d.fly();

        } else {
            nextState = new UTurnPartOneState();
            return null;
        }

    }

    @Override
    public FindDimensionState nextState() {
        //return new LookForEdgeState();
        return nextState;
    }

}
